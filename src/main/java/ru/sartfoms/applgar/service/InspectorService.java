package ru.sartfoms.applgar.service;

import static ru.sartfoms.applgar.util.Constants.HSMO_ROLE;
import static ru.sartfoms.applgar.util.Constants.SMO_ADD_CODE;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import ru.sartfoms.applgar.entity.Inspector;
import ru.sartfoms.applgar.entity.User;
import ru.sartfoms.applgar.repository.InspectorRepository;

@Service
public class InspectorService {
	private final InspectorRepository inspectorRepository;

	public InspectorService(InspectorRepository inspectorRepository) {
		this.inspectorRepository = inspectorRepository;
	}

	public Collection<Inspector> findInspectors(HttpSession session) {
		User user = (User) session.getAttribute("user");
		boolean userHasHsmoRole = user.getRoles().stream().filter(t -> t.getRole_name().equals(HSMO_ROLE))
				.collect(Collectors.toList()).size() > 0;

		Collection<Inspector> result;
		if (userHasHsmoRole) {
			result = inspectorRepository.findByCdSmoAndLoginIsNotNullOrderByFioInsp(user.getSmo() + SMO_ADD_CODE);
		} else {
			result = inspectorRepository.findByCdSmoAndCdFsmoAndLoginIsNotNullOrderByFioInsp(user.getSmo() + SMO_ADD_CODE, user.getfSmo());
		}

		return result;
	}

}

