package ru.sartfoms.applgar.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import ru.sartfoms.applgar.model.UserDTO;

public class ActiveUserStore implements Serializable{
	private static final long serialVersionUID = 1L;
	public Collection<UserDTO> users;

	public ActiveUserStore() {
		users = new ArrayList<UserDTO>();
	}

	public Collection<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(Collection<UserDTO> users) {
		this.users = users;
	}
}
