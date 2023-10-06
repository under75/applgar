package ru.sartfoms.applgar.util;

import java.util.ArrayList;
import java.util.Collection;

public class ActiveUserStore {
	public Collection<String> users;

	public ActiveUserStore() {
		users = new ArrayList<String>();
	}

	public Collection<String> getUsers() {
		return users;
	}

	public void setUsers(Collection<String> users) {
		this.users = users;
	}
}
