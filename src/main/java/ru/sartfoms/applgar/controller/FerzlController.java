package ru.sartfoms.applgar.controller;

import static ru.sartfoms.applgar.service.FerzlService.policyType;
import static ru.sartfoms.applgar.service.FerzlService.resultType;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

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
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.sartfoms.applgar.entity.ASY23MPIError;
import ru.sartfoms.applgar.entity.MPIError;
import ru.sartfoms.applgar.entity.MergeAncessorOip;
import ru.sartfoms.applgar.entity.PersonData;
import ru.sartfoms.applgar.entity.Policy;
import ru.sartfoms.applgar.exception.ExcelGeneratorException;
import ru.sartfoms.applgar.model.AncessorOipParameters;
import ru.sartfoms.applgar.model.PolicySearchParameters;
import ru.sartfoms.applgar.service.FerzlService;
import ru.sartfoms.applgar.util.Info;

@Controller
public class FerzlController {
	private final FerzlService ferzlService;
	@Autowired
	SmartValidator validator;
	@Autowired
	Info info;

	public FerzlController(FerzlService ferzlService) {
		this.ferzlService = ferzlService;
	}

	@ModelAttribute
	public void addInfoToModel(Model model) {
		model.addAttribute("info", info);
	}

	@GetMapping("/policy")
	public String findPolicies(Model model, @RequestParam("clear") Optional<?> clear, HttpSession session)
			throws ParseException {
		if (clear.isPresent()) {
			session.removeAttribute("policySParam");
			session.removeAttribute("policyPage");
		}

		PolicySearchParameters searchParams = (PolicySearchParameters) session.getAttribute("policySParam");
		if (searchParams == null)
			searchParams = new PolicySearchParameters();

		@SuppressWarnings("unchecked")
		Page<PersonData> dataPage = (Page<PersonData>) session.getAttribute("policyPage");
		if (dataPage == null) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			Optional<Integer> page = Optional.of(1);
			dataPage = ferzlService.getPersDataPage(searchParams, userName, page);
		}
		Map<Long, Boolean> requestStatusMap = ferzlService.getRequestStatusAsMap(dataPage);

		model.addAttribute("dataPage", dataPage);
		model.addAttribute("requestStatusMap", requestStatusMap);
		model.addAttribute("formParams", searchParams);
		model.addAttribute("policyTypes", policyType);
		model.addAttribute("dudlTypes", ferzlService.getDudlTypes());

		return "policy-form";
	}

	@PostMapping("/policy")
	public String findPolicies(Model model, @ModelAttribute("formParams") PolicySearchParameters searchParams,
			BindingResult bindingResult, @RequestParam("page") Optional<Integer> page, HttpSession session)
			throws ParseException {

		model.addAttribute("policyTypes", policyType);
		model.addAttribute("dudlTypes", ferzlService.getDudlTypes());

		if (!page.isPresent()) {
			ferzlService.validate(searchParams, bindingResult);
			validator.validate(searchParams, bindingResult);
		}

		String userName = SecurityContextHolder.getContext().getAuthentication().getName();

		if (!bindingResult.hasErrors() && !page.isPresent()) {
			ferzlService.saveRequest(searchParams, userName);
		}

		Page<PersonData> dataPage = ferzlService.getPersDataPage(searchParams, userName, page);
		Map<Long, Boolean> requestStatusMap = ferzlService.getRequestStatusAsMap(dataPage);

		model.addAttribute("dataPage", dataPage);
		model.addAttribute("requestStatusMap", requestStatusMap);

		session.setAttribute("policyPage", dataPage);
		session.setAttribute("policySParam", searchParams);

		return "policy-form";
	}

	@PostMapping("/policy/res")
	public String getPolicy(Model model, @RequestParam(value = "rid") Long rid) {
		Collection<MPIError> errors = ferzlService.findErrorsByRid(rid);
		if (errors.size() > 0) {
			model.addAttribute("errors", errors);
			return "mpi-err";
		} else if (!ferzlService.getRequestStatusByRid(rid)) {
			MPIError slovenly = new MPIError();
			slovenly.setCode("slovenly");
			slovenly.setMessage("Ошибка во введенных данных, проверьте корректность ввода");
			errors.add(slovenly);
			model.addAttribute("errors", errors);
			return "mpi-err";
		}

		Collection<Policy> policies = ferzlService.findPoliciesByRid(rid);
		if (policies.size() == 0) {
			MPIError notfound = new MPIError();
			notfound.setCode("not_found");
			notfound.setMessage("Действующего полиса не найдено");
			errors.add(notfound);
			model.addAttribute("errors", errors);
			return "mpi-err";
		}

		model.addAttribute("personData", ferzlService.getPersonDataByRid(rid));
		model.addAttribute("resultTypes", resultType);
		model.addAttribute("persons", ferzlService.findPersonsByRid(rid));
		model.addAttribute("policies", policies);
		model.addAttribute("rid", rid);

		return "policy-res";
	}

	@GetMapping("/policy/res/excel")
	@ResponseBody
	public ResponseEntity<?> policyToExcel(Model model, @RequestParam("rid") Long rid) {
		ResponseEntity<?> resource;
		try {
			resource = ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report" + rid + ".xlsx")
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
					.body(new InputStreamResource(ferzlService.createExcel(rid)));
		} catch (IOException | ExcelGeneratorException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return resource;
	}

	@PostMapping("/policy/excel")
	@ResponseBody
	public ResponseEntity<?> policiesToExcel(Model model,
			@ModelAttribute("formParams") PolicySearchParameters searchParams) {
		if (searchParams.getSelectedRows() == null) {
			return new ResponseEntity<String>("<h1>Не выбраны строки для отчета в Excel</h1>", HttpStatus.BAD_REQUEST);
		}
		ResponseEntity<?> resource;
		try {
			resource = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.xlsx")
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
					.body(new InputStreamResource(ferzlService.createExcel(searchParams.getSelectedRows())));
		} catch (IOException | ExcelGeneratorException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return resource;
	}

	@GetMapping("/ancessor")
	public String mergeAncessorOip(Model model) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();

		AncessorOipParameters formParams = new AncessorOipParameters();
		model.addAttribute("formParams", formParams);

		Optional<Integer> page = Optional.of(1);
		Page<MergeAncessorOip> dataPage = ferzlService.getDataPage(formParams, userName, page);
		model.addAttribute("dataPage", dataPage);

		return "ancessor-oip";

	}

	@PostMapping("/ancessor")
	public String mergeAncessorOip(Model model, @ModelAttribute("formParams") AncessorOipParameters searchParams,
			BindingResult bindingResult, @RequestParam("page") Optional<Integer> page) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();

		if (!page.isPresent())
			validator.validate(searchParams, bindingResult);

		if (!bindingResult.hasErrors() && !page.isPresent())
			ferzlService.saveRequest(searchParams, userName);

		Page<MergeAncessorOip> dataPage = ferzlService.getDataPage(searchParams, userName, page);
		model.addAttribute("dataPage", dataPage);

		return "ancessor-oip";

	}

	@PostMapping("/ancessor/res")
	public String ancessorOipResult(Model model, @RequestParam("rid") Long rid) {

		Collection<ASY23MPIError> errors = ferzlService._findErrorsByRid(rid);
		if (errors.size() > 0) {
			model.addAttribute("errors", errors);
			return "mpi-err";
		}

		return "error/404";
	}
}
