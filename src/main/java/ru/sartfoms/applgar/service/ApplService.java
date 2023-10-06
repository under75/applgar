package ru.sartfoms.applgar.service;

import static ru.sartfoms.applgar.util.Constants.HSMO_ROLE;
import static ru.sartfoms.applgar.util.Constants.SMO_ADD_CODE;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ru.sartfoms.applgar.dao.ApplDAO;
import ru.sartfoms.applgar.entity.Appl;
import ru.sartfoms.applgar.entity.Fsmo;
import ru.sartfoms.applgar.entity.Inspector;
import ru.sartfoms.applgar.entity.User;
import ru.sartfoms.applgar.exception.ExcelGeneratorException;
import ru.sartfoms.applgar.model.ApplRowData;
import ru.sartfoms.applgar.model.ApplSearchParameters;
import ru.sartfoms.applgar.repository.ApplRepository;
import ru.sartfoms.applgar.repository.FsmoRepository;
import ru.sartfoms.applgar.repository.InspectorRepository;

@Service
public class ApplService {
	private static final Integer PAGE_SIZE = 10;
	private final ApplRepository applRepository;
	private final InspectorRepository inspectorRepository;
	private final FsmoRepository smoRepository;
	private final ApplDAO applDAO;

	public ApplService(ApplRepository applRepository, ApplDAO applDAO, InspectorRepository inspectorRepository,
			FsmoRepository smoRepository) {
		this.applRepository = applRepository;
		this.inspectorRepository = inspectorRepository;
		this.smoRepository = smoRepository;
		this.applDAO = applDAO;
	}

	public Page<Appl> findByDtApplBetweenAndCdSmo(LocalDate start, LocalDate end, Integer cdSmo, Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmo(start, end, cdSmo, pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndSerDoc(LocalDate start, LocalDate end, Integer cdSmo, String serDoc,
			Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndSerDoc(start, end, cdSmo, serDoc, pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndSerDocAndNumDoc(LocalDate start, LocalDate end, Integer cdSmo,
			String serDoc, String numDoc, Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndSerDocAndNumDoc(start, end, cdSmo, serDoc, numDoc,
				pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmo(LocalDate start, LocalDate end, Integer cdSmo,
			Integer cdFsmo, Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndCdFsmo(start, end, cdSmo, cdFsmo, pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDoc(LocalDate start, LocalDate end, Integer cdSmo,
			Integer cdFsmo, String serDoc, Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDoc(start, end, cdSmo, cdFsmo, serDoc,
				pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndNumDoc(LocalDate start, LocalDate end,
			Integer cdSmo, Integer cdFsmo, String serDoc, String numDoc, Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndNumDoc(start, end, cdSmo, cdFsmo, serDoc,
				numDoc, pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndInspector(LocalDate start, LocalDate end, Integer cdSmo,
			Inspector inspector, Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndInspector(start, end, cdSmo, inspector, pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndSerDocAndInspector(LocalDate start, LocalDate end, Integer cdSmo,
			String serDoc, Inspector inspector, Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndSerDocAndInspector(start, end, cdSmo, serDoc, inspector,
				pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndSerDocAndNumDocAndInspector(LocalDate start, LocalDate end,
			Integer cdSmo, String serDoc, String numDoc, Inspector inspector, Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndSerDocAndNumDocAndInspector(start, end, cdSmo, serDoc,
				numDoc, inspector, pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmoAndInspector(LocalDate start, LocalDate end, Integer cdSmo,
			Integer cdFsmo, Inspector inspector, Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndCdFsmoAndInspector(start, end, cdSmo, cdFsmo, inspector,
				pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndInspector(LocalDate start, LocalDate end,
			Integer cdSmo, Integer cdFsmo, String serDoc, Inspector inspector, Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndInspector(start, end, cdSmo, cdFsmo,
				serDoc, inspector, pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndNumDocAndInspector(LocalDate start, LocalDate end,
			Integer cdSmo, Integer cdFsmo, String serDoc, String numDoc, Inspector inspector, Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndNumDocAndInspector(start, end, cdSmo,
				cdFsmo, serDoc, numDoc, inspector, pageable);
	}

	public Optional<Appl> findById(Long applId) {
		return applRepository.findById(applId);
	}

	public Page<Appl> getPage(ApplSearchParameters applSParam, Optional<Integer> page, 
			User user) throws ParseException {
		LocalDate start = LocalDate.parse(applSParam.getDtReg1());
		LocalDate end = LocalDate.parse(applSParam.getDtReg2()).plusDays(1);
		String serDoc = applSParam.getSerDoc().trim();
		String numDoc = applSParam.getNumDoc().trim();
		int currentPage = page.orElse(1);
		boolean userHasHsmoRole = user.getRoles().stream().filter(t -> t.getRole_name().equals(HSMO_ROLE))
				.collect(Collectors.toList()).size() > 0;

		Page<Appl> applPage;
		if (applSParam.getCdInsp() != null) {
			Inspector inspector = inspectorRepository.getReferenceById(applSParam.getCdInsp());
			if (!serDoc.isEmpty() && !numDoc.isEmpty()) {
				applPage = findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndNumDocAndInspector(start, end,
						user.getSmo() + SMO_ADD_CODE, inspector.getCdFsmo(), serDoc, numDoc, inspector,
						PageRequest.of(currentPage - 1, PAGE_SIZE));
			} else if (!serDoc.isEmpty()) {
				applPage = findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndInspector(start, end,
						user.getSmo() + SMO_ADD_CODE, inspector.getCdFsmo(), serDoc, inspector,
						PageRequest.of(currentPage - 1, PAGE_SIZE));
			} else {
				applPage = findByDtApplBetweenAndCdSmoAndCdFsmoAndInspector(start, end, user.getSmo() + SMO_ADD_CODE,
						inspector.getCdFsmo(), inspector, PageRequest.of(currentPage - 1, PAGE_SIZE));
			}
		} else if (applSParam.getCdFsmo() != null) {
			Fsmo fSmo = smoRepository.getByCdSmoAndCdFsmo(user.getSmo() + SMO_ADD_CODE, applSParam.getCdFsmo());
			if (!serDoc.isEmpty() && !numDoc.isEmpty()) {
				applPage = findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndNumDoc(start, end,
						user.getSmo() + SMO_ADD_CODE, fSmo.getCdFsmo(), serDoc, numDoc,
						PageRequest.of(currentPage - 1, PAGE_SIZE));
			} else if (!serDoc.isEmpty()) {
				applPage = findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDoc(start, end, user.getSmo() + SMO_ADD_CODE,
						fSmo.getCdFsmo(), serDoc, PageRequest.of(currentPage - 1, PAGE_SIZE));
			} else {
				applPage = findByDtApplBetweenAndCdSmoAndCdFsmo(start, end, user.getSmo() + SMO_ADD_CODE,
						fSmo.getCdFsmo(), PageRequest.of(currentPage - 1, PAGE_SIZE));
			}
		} else {
			if (userHasHsmoRole) {
				if (!serDoc.isEmpty() && !numDoc.isEmpty()) {
					applPage = findByDtApplBetweenAndCdSmoAndSerDocAndNumDoc(start, end, user.getSmo() + SMO_ADD_CODE,
							serDoc, numDoc, PageRequest.of(currentPage - 1, PAGE_SIZE));
				} else if (!serDoc.isEmpty()) {
					applPage = findByDtApplBetweenAndCdSmoAndSerDoc(start, end, user.getSmo() + SMO_ADD_CODE, serDoc,
							PageRequest.of(currentPage - 1, PAGE_SIZE));
				} else {
					applPage = findByDtApplBetweenAndCdSmo(start, end, user.getSmo() + SMO_ADD_CODE,
							PageRequest.of(currentPage - 1, PAGE_SIZE));
				}
			} else {
				if (!serDoc.isEmpty() && !numDoc.isEmpty()) {
					applPage = findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndNumDoc(start, end,
							user.getSmo() + SMO_ADD_CODE, user.getfSmo(), serDoc, numDoc,
							PageRequest.of(currentPage - 1, PAGE_SIZE));
				} else if (!serDoc.isEmpty()) {
					applPage = findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDoc(start, end, user.getSmo() + SMO_ADD_CODE,
							user.getfSmo(), serDoc, PageRequest.of(currentPage - 1, PAGE_SIZE));
				} else {
					applPage = findByDtApplBetweenAndCdSmoAndCdFsmo(start, end, user.getSmo() + SMO_ADD_CODE,
							user.getfSmo(), PageRequest.of(currentPage - 1, PAGE_SIZE));
				}
			}
		}

		return applPage;
	}

	public ByteArrayInputStream createExcel(ApplSearchParameters applSParam, HttpSession session)
			throws ExcelGeneratorException, IOException {

		User user = (User) session.getAttribute("user");
		return new ApplExcelGenerator(getDataForExcel(applSParam, user)).toExcel();

	}

	private Collection<ApplRowData> getDataForExcel(ApplSearchParameters applSParam, User user) {

		return applDAO.getDataForExcel(user, applSParam);

	}
}
