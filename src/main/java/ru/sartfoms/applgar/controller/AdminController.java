package ru.sartfoms.applgar.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.sartfoms.applgar.entity.User;
import ru.sartfoms.applgar.model.UserDTO;
import ru.sartfoms.applgar.service.UserService;
import ru.sartfoms.applgar.util.ActiveUserStore;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	ActiveUserStore activeUserStore;
	private final UserService userService;

	public AdminController(UserService userService) {
		this.userService = userService;
	}

	@ModelAttribute
	public void addUsersToModel(Model model) {
		Collection<UserDTO> users = activeUserStore.getUsers();
		users.forEach(t -> {
			if (!t.getId().equals("admin")) {
				User entity = userService.getByName(t.getId());
				t.setFirstname(entity.getFirstname());
				t.setLastname(entity.getLastname());
				t.setPatronymic(entity.getPatronymic());
				t.setSmo(entity.getSmo());
				t.setfSmo(entity.getfSmo());
			}
		});
		model.addAttribute("users", users);
	}
	
	@GetMapping
	public String getLoggedUsers() {
		return "admin";
	}
}
