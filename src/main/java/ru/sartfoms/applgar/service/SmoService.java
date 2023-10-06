package ru.sartfoms.applgar.service;

import static ru.sartfoms.applgar.util.Constants.HSMO_ROLE;
import static ru.sartfoms.applgar.util.Constants.SMO_ADD_CODE;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import ru.sartfoms.applgar.entity.Fsmo;
import ru.sartfoms.applgar.entity.User;
import ru.sartfoms.applgar.repository.FsmoRepository;

@Service
public class SmoService {
	private final FsmoRepository fsmoRepository;

	public SmoService(FsmoRepository fsmoRepository) {
		super();
		this.fsmoRepository = fsmoRepository;
	}

	public Collection<Fsmo> findBranches(User user) {
		Collection<Fsmo> result = null;

		if (user.getRoles().stream().anyMatch(t -> t.getRole_name().equals(HSMO_ROLE))) {
			result = fsmoRepository.findByCdSmoOrderByName(user.getSmo() + SMO_ADD_CODE);
		} else {
			result = new ArrayList<>();
			result.add(fsmoRepository.getByCdSmoAndCdFsmo(user.getSmo() + SMO_ADD_CODE, user.getfSmo()));
		}

		return result;
	}

}
