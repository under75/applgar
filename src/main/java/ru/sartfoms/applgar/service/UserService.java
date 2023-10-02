package ru.sartfoms.applgar.service;

import org.springframework.stereotype.Service;

import ru.sartfoms.applgar.entity.User;
import ru.sartfoms.applgar.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User getByName(String name) {
		return userRepository.getReferenceById(name);
	}

}
