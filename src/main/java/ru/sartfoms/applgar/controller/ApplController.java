package ru.sartfoms.applgar.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ru.sartfoms.applgar.entity.Appl;
import ru.sartfoms.applgar.entity.User;
import ru.sartfoms.applgar.exception.ExcelGeneratorException;
import ru.sartfoms.applgar.model.ApplSearchParameters;
import ru.sartfoms.applgar.model.FilterWord;
import ru.sartfoms.applgar.model.Gar;
import ru.sartfoms.applgar.model.SelectedAddress;
import ru.sartfoms.applgar.service.AddressService;
import ru.sartfoms.applgar.service.ApplService;
import ru.sartfoms.applgar.service.InspectorService;
import ru.sartfoms.applgar.service.SmoService;
import ru.sartfoms.applgar.service.UserService;
import ru.sartfoms.applgar.util.DateValidator;
import ru.sartfoms.applgar.util.Info;

@Controller
public class ApplController {
	private final ApplService applService;
	private final AddressService<?> addrService;
	private final InspectorService inspectorService;
	private final SmoService smoService;
	private final UserService userService;
	@Autowired
	Info info;

	public ApplController(ApplService service, AddressService<?> addrService, InspectorService inspectorService,
			SmoService smoService, UserService userService) {
		this.applService = service;
		this.addrService = addrService;
		this.inspectorService = inspectorService;
		this.smoService = smoService;
		this.userService = userService;
	}
	
	@ModelAttribute
	public void addInfoToModel(Model model) {
		model.addAttribute("info", info);
	}

	@GetMapping("/appl")
	public String index(Model model, @RequestParam("clear") Optional<?> clear, HttpSession session)
			throws ParseException {

		if (session.getAttribute("user") == null) {
			User user = userService.getByName(SecurityContextHolder.getContext().getAuthentication().getName());
			session.setAttribute("user", user);
		} else if (clear.isPresent()) {
			session.removeAttribute("applSParam");
			session.removeAttribute("applPage");
		}

		ApplSearchParameters applSParam = (ApplSearchParameters) session.getAttribute("applSParam");
		if (applSParam != null) {
			@SuppressWarnings("unchecked")
			Page<Appl> applPage = (Page<Appl>) session.getAttribute("applPage");
			model.addAttribute("dataPage", applPage);
		} else {
			applSParam = new ApplSearchParameters();
		}

		model.addAttribute("formParams", applSParam);
		User user = (User) session.getAttribute("user");
		model.addAttribute("inspectors", inspectorService.findInspectors(user));
		model.addAttribute("branches", smoService.findBranches(user));

		return "appl-form";
	}

	@PostMapping("/appl")
	public String index(Model model, @ModelAttribute("formParams") @Valid ApplSearchParameters applSParam,
			BindingResult bindingResult, @RequestParam("page") Optional<Integer> page, HttpSession session)
			throws ParseException {
		User user = (User) session.getAttribute("user");
		model.addAttribute("inspectors", inspectorService.findInspectors(user));
		model.addAttribute("branches", smoService.findBranches(user));
		if (!DateValidator.isValid(applSParam.getDtReg1())) {
			bindingResult.rejectValue("dtReg1", "Invalid date");
		}
		if (!DateValidator.isValid(applSParam.getDtReg2())) {
			bindingResult.rejectValue("dtReg2", "Invalid date");
		}

		if (!bindingResult.hasErrors()) {
			Page<Appl> applPage = applService.getPage(applSParam, page, user);
			model.addAttribute("dataPage", applPage);

			session.setAttribute("applPage", applPage);
			session.setAttribute("applSParam", applSParam);
		}

		return "appl-form";
	}

	@PostMapping("/appl/addr")
	public String address(Model model, @RequestParam("idAppl") Long applId, @ModelAttribute("gar") Gar gar,
			@ModelAttribute("selAddress") SelectedAddress selAddress, @ModelAttribute("filter") FilterWord filter,
			@RequestParam("save") Optional<String> save) {

		Appl appl = applService.findById(applId).orElse(null);
		if (appl == null) {
			return "redirect:/";
		}
		model.addAttribute("appl", appl);

		String okato_reg = "-";
		String okato_pr = "-";
		if (appl.getId_adrreg2() != null) {
			okato_reg = addrService.findOkato(appl.getId_adrreg2());
		} else if (appl.getId_adrreg() != null) {
			okato_reg = addrService.findOkato(appl.getId_adrreg());
		}
		if (appl.getId_adrpr2() != null) {
			okato_pr = addrService.findOkato(appl.getId_adrpr2());
		} else if (appl.getId_adrpr() != null) {
			okato_pr = addrService.findOkato(appl.getId_adrpr());
		}
		model.addAttribute("okato_reg", okato_reg);
		model.addAttribute("okato_pr", okato_pr);

		if (selAddress.getIdlev1Reg() == null && selAddress.getIdlev1Pr() == null
				&& addrService.isRecordExist(appl.getId_appl())) {
			addrService.initFromDB(selAddress, appl.getId_appl());
		}

		addrService.fillPrIfSameWithReg(appl, selAddress);
		addrService.setGar(appl, gar, filter, selAddress);

		/** Save result **/
		if (save.isPresent()) {
			model.addAttribute("saved", addrService.saveResult(appl, selAddress, gar));
		}

		return "addr-form";
	}

	@PostMapping("/appl/report")
	@ResponseBody
	public ResponseEntity<?> download(@ModelAttribute("applSParam") @Valid ApplSearchParameters applSParam,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors())
			return ResponseEntity.badRequest().build();

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();

		try {
			session.setAttribute("status", "busy");
			ResponseEntity<?> resource = ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.xlsx")
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
					.body(new InputStreamResource(applService.createExcel(applSParam, session)));
			session.setAttribute("status", "done");
			return resource;
		} catch (ExcelGeneratorException | IOException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/appl/report/status")
	@ResponseBody
	public String checkDStatus() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return (String) attr.getRequest().getSession().getAttribute("status");
	}
}
