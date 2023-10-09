package ru.sartfoms.applgar.util;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.stereotype.Component;

import ru.sartfoms.applgar.model.UserDTO;

@Component
public class LoggedUser implements HttpSessionBindingListener, Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private ActiveUserStore activeUserStore;

	public LoggedUser(String username, ActiveUserStore activeUserStore) {
		this.username = username;
		this.activeUserStore = activeUserStore;
	}

	public LoggedUser() {
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		Collection<UserDTO> users = activeUserStore.getUsers();
		LoggedUser loggedUser = (LoggedUser) event.getValue();
		if (!users.stream().anyMatch(t -> t.getId().equals(loggedUser.getUsername()))) {
			UserDTO user = new UserDTO();
			user.setEffDate(LocalDateTime.now());
			user.setId(loggedUser.getUsername());
			users.add(user);
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		Collection<UserDTO> users = activeUserStore.getUsers();
		LoggedUser loggedUser = (LoggedUser) event.getValue();
		users.removeIf(t -> t.getId().equals(loggedUser.getUsername()));
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ActiveUserStore getActiveUserStore() {
		return activeUserStore;
	}

	public void setActiveUserStore(ActiveUserStore activeUserStore) {
		this.activeUserStore = activeUserStore;
	}
}
