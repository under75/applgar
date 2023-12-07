package ru.sartfoms.applgar.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import ru.sartfoms.applgar.dao.PersonDataDAO;
import ru.sartfoms.applgar.entity.DudlType;
import ru.sartfoms.applgar.entity.MPIError;
import ru.sartfoms.applgar.entity.MergeAncessorOip;
import ru.sartfoms.applgar.entity.Person;
import ru.sartfoms.applgar.entity.PersonData;
import ru.sartfoms.applgar.entity.Policy;
import ru.sartfoms.applgar.exception.ExcelGeneratorException;
import ru.sartfoms.applgar.model.AncessorOipParameters;
import ru.sartfoms.applgar.model.PersonDataForRequestValidation;
import ru.sartfoms.applgar.model.PolicyRowData;
import ru.sartfoms.applgar.model.PolicySearchParameters;
import ru.sartfoms.applgar.repository.DudlTypeRepository;
import ru.sartfoms.applgar.repository.MPIErrorRepository;
import ru.sartfoms.applgar.repository.MergeAncessorOipRepository;
import ru.sartfoms.applgar.repository.PersonDataRepository;
import ru.sartfoms.applgar.repository.PersonRepository;
import ru.sartfoms.applgar.repository.PolicyRepository;
import ru.sartfoms.applgar.util.DateValidator;
import static ru.sartfoms.applgar.util.Constants.*;

@Service
public class FerzlService {
	private final DudlTypeRepository dudlTypeRepository;
	private final PersonDataDAO personDataDAO;
	private final PersonRepository personRepository;
	private final PersonDataRepository personDataRepository;
	private final MergeAncessorOipRepository mergeAncessorOipRepository;
	private final MPIErrorRepository errorRepository;
	private final PolicyRepository policyRepository;

	public enum Show {
		Person, OmsPolicy, Dudl, Address, Attach, Contact, Snils, SocialStatus, Ern, All
	}

	private static final Integer PAGE_SIZE = 10;
	public final static Map<Show, String> resultType = new LinkedHashMap<>();
	{
		resultType.put(Show.Person, "Информация о персоне");
		resultType.put(Show.OmsPolicy, "Информация о полисе");
		resultType.put(Show.Dudl, "ДУДЛ");
		resultType.put(Show.Address, "Адрес");
		resultType.put(Show.Attach, "Прикрепление");
		resultType.put(Show.Contact, "Данные контакта");
		resultType.put(Show.Snils, "Данные СНИЛС");
		resultType.put(Show.SocialStatus, "Данные соц. статуса");
		resultType.put(Show.Ern, "Данные ЕРН");
	}

	public FerzlService(DudlTypeRepository dudlTypeRepository, PersonDataDAO personDataDAO,
			PersonRepository personRepository, PersonDataRepository personDataRepository,
			MergeAncessorOipRepository mergeAncessorOipRepository, PolicyRepository policyRepository,
			MPIErrorRepository errorRepository) {
		this.dudlTypeRepository = dudlTypeRepository;
		this.personDataDAO = personDataDAO;
		this.personRepository = personRepository;
		this.personDataRepository = personDataRepository;
		this.mergeAncessorOipRepository = mergeAncessorOipRepository;
		this.errorRepository = errorRepository;
		this.policyRepository = policyRepository;
	}

	public final static Map<String, String> policyType = new HashMap<>();
	{
		policyType.put("С", "Полис ОМС старого образца");
		policyType.put("В", "Временное свидетельство в форме бумажного бланка");
		policyType.put("Е", "Временное свидетельство в форме электронного документа");
		policyType.put("П", "Бумажный полис ОМС единого образца");
		policyType.put("Э", "Электронный полис ОМС единого образца");
		policyType.put("К", "Полис ОМС в составе универсальной электронной карты");
		policyType.put("Ц", "Цифровой полис ОМС");
		policyType.put("Х", "Состояние на учёте без полиса ОМС");
		policyType.put("М", "Состояние на учёте МФЦ");
	}

	public Collection<DudlType> getDudlTypes() {
		return dudlTypeRepository.findAll();
	}

	public Map<Long, Boolean> getRequestStatusAsMap(Page<PersonData> persDataPage) {
		Map<Long, Boolean> map = new HashMap<>();
		persDataPage.forEach(t -> {
			PersonDataForRequestValidation data = getPersonDataForRequestValidation(t.getRid());
			if (data != null) {
				t.setBirthDay(data.getBirthDay());
				t.setLastName(data.getLastName());
				t.setFirstName(data.getFirstName());
				t.setPatronymic(data.getPatronymic());
				if (t != null && t.getHasError() != null && !t.getHasError()) {
					Collection<Person> persons = findPersonsByRid(t.getRid());
					map.put(t.getRid(), isRequestValid(data, persons));
				}
			}
		});

		return map;
	}

	public PersonDataForRequestValidation getPersonDataForRequestValidation(Long rid) {
		return personDataDAO.getPersonDataForRequestValidation(rid);
	}

	public Collection<Person> findPersonsByRid(Long rid) {
		return personRepository.findByRid(rid);
	}

	private Boolean isRequestValid(PersonDataForRequestValidation data, Collection<Person> persons) {
		Boolean res = false;
		if (data != null && data.getPatronymic() == null)
			data.setPatronymic("");
		for (Person p : persons) {
			if (p.getPatronymic() != null && p.getBirthDay() != null && p.getLastName() != null
					&& p.getFirstName() != null) {
				if (data.getBirthDay().equals(p.getBirthDay())
						&& data.getLastName().trim().toLowerCase().equals(p.getLastName().trim().toLowerCase())
						&& data.getFirstName().trim().toLowerCase().equals(p.getFirstName().trim().toLowerCase())
						&& data.getPatronymic().trim().toLowerCase().equals(p.getPatronymic().trim().toLowerCase())) {
					res = true;
				}
			}
		}

		return res;
	}

	public void validate(PolicySearchParameters persSParam, BindingResult bindingResult) {
		if (!DateValidator.isValid(persSParam.getBirthDay())) {
			bindingResult.rejectValue("birthDay", "");
		}
		if (persSParam.getDt() != null && !persSParam.getDt().isEmpty() && !DateValidator.isValid(persSParam.getDt())) {
			bindingResult.rejectValue("dt", "");
		}
		if (persSParam.getDtFrom() != null && !persSParam.getDtFrom().isEmpty()
				&& !DateValidator.isValid(persSParam.getDtFrom())) {
			bindingResult.rejectValue("dtFrom", "");
		}
		if (persSParam.getDtTo() != null && !persSParam.getDtTo().isEmpty()
				&& !DateValidator.isValid(persSParam.getDtTo())) {
			bindingResult.rejectValue("dtTo", "");
		}
		if (persSParam.getPolicyNum().trim().isEmpty() && persSParam.getDudlNum().trim().isEmpty()
				&& persSParam.getSnils().trim().isEmpty()) {
			bindingResult.addError(new ObjectError("globalError",
					"Для успешного поиска, необходимо ввести данные одного из документов(Полис, ДУдЛ, СНИЛС)"));
		} else if (persSParam.getDudlType() != null && persSParam.getDudlNum().trim().isEmpty()) {
			bindingResult.rejectValue("dudlNum", "");
		} else if (!persSParam.getDudlNum().trim().isEmpty() && persSParam.getDudlType() == null) {
			bindingResult.rejectValue("dudlType", "");
		}
	}

	public PersonData saveRequest(PolicySearchParameters searchParams, String userName) throws ParseException {
		PersonData personData = new PersonData();
		personData.setPcyType(searchParams.getPolicyType());
		personData.setPcySer(searchParams.getPolicySer().trim());
		personData.setPcy(searchParams.getPolicyNum().trim());
		if (searchParams.getDudlType() != null)
			personData.setDudlType(searchParams.getDudlType());
		personData.setDudlSer(searchParams.getDudlSer().trim());
		personData.setDudlNum(searchParams.getDudlNum().trim());
		personData.setSnils(searchParams.getSnils().trim());
		personData.setHistorical(searchParams.getHistorical() == true ? searchParams.getHistorical() : null);
		if (searchParams.getHistorical()) {
			if (searchParams.getDtFrom() != null && !searchParams.getDtFrom().isEmpty())
				personData.setDtFrom(LocalDate.parse(searchParams.getDtFrom()));
			if (searchParams.getDtTo() != null && !searchParams.getDtTo().isEmpty())
				personData.setDtTo(LocalDate.parse(searchParams.getDtTo()).plusDays(1));
		} else if (searchParams.getDt() != null && !searchParams.getDt().isEmpty()) {
			personData.setDt(LocalDate.parse(searchParams.getDt()));
		}
		personData.setUser(userName);
		personData.setShow(Show.Person + " " + Show.OmsPolicy);
		personData.setDtIns(LocalDateTime.now());
		personData.setOwner(OWNER_REST);

		personData = personDataRepository.save(personData);
		personDataDAO.save(personData.getRid(), searchParams.getLastName().trim(), searchParams.getFirstName().trim(),
				searchParams.getPatronymic().trim(), LocalDate.parse(searchParams.getBirthDay()));
		
		return personData;
	}

	public Page<PersonData> getPersDataPage(PolicySearchParameters searchParams, String userName,
			Optional<Integer> page) throws ParseException {
		int currentPage = page.orElse(1);
		Page<PersonData> dataPage;
		PageRequest pageRequest = PageRequest.of(currentPage - 1, PAGE_SIZE);
		if (searchParams.getDateFrom() != null && !searchParams.getDateFrom().isEmpty()
				&& searchParams.getDateTo() != null && !searchParams.getDateTo().isEmpty()
				&& DateValidator.isValid(searchParams.getDateFrom())
				&& DateValidator.isValid(searchParams.getDateTo())) {
			LocalDateTime start = LocalDate.parse(searchParams.getDateFrom()).atStartOfDay();
			LocalDateTime end = LocalDate.parse(searchParams.getDateTo()).plusDays(1).atStartOfDay();
			dataPage = personDataRepository.findByUserAndDtInsBetweenOrderByDtInsDesc(userName, start, end,
					pageRequest);
		} else if (searchParams.getDateFrom() != null && !searchParams.getDateFrom().isEmpty()
				&& DateValidator.isValid(searchParams.getDateFrom())) {
			LocalDateTime start = LocalDate.parse(searchParams.getDateFrom()).atStartOfDay();
			dataPage = personDataRepository.findByUserAndDtInsAfterOrderByDtInsDesc(userName, start, pageRequest);
		} else if (searchParams.getDateTo() != null && !searchParams.getDateTo().isEmpty()
				&& DateValidator.isValid(searchParams.getDateTo())) {
			LocalDateTime end = LocalDate.parse(searchParams.getDateTo()).plusDays(1).atStartOfDay();
			dataPage = personDataRepository.findByUserAndDtInsBeforeOrderByDtInsDesc(userName, end, pageRequest);
		} else {
			dataPage = personDataRepository.findByUserOrderByDtInsDesc(userName, pageRequest);
		}

		return dataPage;
	}

	public Collection<MPIError> findErrorsByRid(Long rid) {
		return errorRepository.findAllByRid(rid);
	}

	public boolean getRequestStatusByRid(Long rid) {
		PersonDataForRequestValidation data = getPersonDataForRequestValidation(rid);
		Collection<Person> persons = findPersonsByRid(rid);

		return isRequestValid(data, persons);
	}

	public Collection<Policy> findPoliciesByRid(Long rid) {
		return policyRepository.findAllByRid(rid);
	}

	public PersonData getPersonDataByRid(Long rid) {
		return personDataRepository.getReferenceById(rid);
	}

	public InputStream createExcel(Long rid) throws IOException, ExcelGeneratorException {
		return new PolicyExcelGenerator(getDataForExcel(rid)).toExcel();
	}

	private Collection<PolicyRowData> getDataForExcel(Long rid) {
		Collection<PolicyRowData> result = new HashSet<>();

		Collection<Policy> policies = findPoliciesByRid(rid);

		PolicyRowData row;
		for (Policy pol : policies) {
			row = new PolicyRowData();
			row.setEnp(pol.getEnp());
			row.setPcyEffDt(pol.getPcyDateB());
			row.setPcyType(pol.getPcyType());
			row.setPcyStatus(pol.getPcyStatus());
			row.setDsourceType(pol.getDsourceType());
			row.setGender(pol.getGender() == 1 ? "М" : "Ж");
			row.setLastName(pol.getLastName());
			row.setFirstName(pol.getFirstName());
			row.setPatronymic(pol.getPatronymic());
			row.setBirthDay(pol.getBirthDay());
			row.setBlankNum(pol.getBlankNum());
			row.setPcyExpDt(pol.getPcyDateE());

			result.add(row);
		}
		try {
			return result.stream().sorted(Comparator.comparing(PolicyRowData::getPcyEffDt))
					.collect(Collectors.toList());
		} catch (NullPointerException e) {
			return result;
		}
	}

	public InputStream createExcel(Collection<Long> selectedRows) throws IOException, ExcelGeneratorException {
		Collection<PolicyRowData> all = new ArrayList<>();
		for (Long rid : selectedRows) {
			all.addAll(getDataForExcel(rid));
		}

		return new PolicyExcelGenerator(all).toExcel();
	}

	public Page<MergeAncessorOip> getDataPage(AncessorOipParameters searchParams, String userName,
			Optional<Integer> page) {
		int currentPage = page.orElse(1);
		Page<MergeAncessorOip> dataPage;
		PageRequest pageRequest = PageRequest.of(currentPage - 1, PAGE_SIZE);
		if (searchParams.getDateFrom() != null && !searchParams.getDateFrom().isEmpty()
				&& searchParams.getDateTo() != null && !searchParams.getDateTo().isEmpty()
				&& DateValidator.isValid(searchParams.getDateFrom())
				&& DateValidator.isValid(searchParams.getDateTo())) {
			LocalDateTime start = LocalDate.parse(searchParams.getDateFrom()).atStartOfDay();
			LocalDateTime end = LocalDate.parse(searchParams.getDateTo()).plusDays(1).atStartOfDay();
			dataPage = mergeAncessorOipRepository.findByUserAndDtInsBetweenOrderByDtInsDesc(userName,
					start, end, pageRequest);
		} else if (searchParams.getDateFrom() != null && !searchParams.getDateFrom().isEmpty()
				&& DateValidator.isValid(searchParams.getDateFrom())) {
			LocalDateTime start = LocalDate.parse(searchParams.getDateFrom()).atStartOfDay();
			dataPage = mergeAncessorOipRepository.findByUserAndDtInsAfterOrderByDtInsDesc(userName,
					start, pageRequest);
		} else if (searchParams.getDateTo() != null && !searchParams.getDateTo().isEmpty()
				&& DateValidator.isValid(searchParams.getDateTo())) {
			LocalDateTime end = LocalDate.parse(searchParams.getDateTo()).plusDays(1).atStartOfDay();
			dataPage = mergeAncessorOipRepository.findByUserAndDtInsBeforeOrderByDtInsDesc(userName, end,
					pageRequest);
		} else {
			dataPage = mergeAncessorOipRepository.findByUserOrderByDtInsDesc(userName, pageRequest);
		}

		return dataPage;
	}

	public void saveRequest(AncessorOipParameters searchParams, String userName) {
		MergeAncessorOip entity = new MergeAncessorOip();
		entity.setOip(searchParams.getOip());
		entity.setDtIns(LocalDateTime.now());
		entity.setUser(userName);

		mergeAncessorOipRepository.save(entity);
	}

	public PersonData save(PersonData personData) {
		return personDataRepository.save(personData);
	}
}
