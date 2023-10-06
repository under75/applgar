package ru.sartfoms.applgar.util;

import java.io.Serializable;
import java.util.Collection;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.stereotype.Component;

@Component
public class LoggedUser implements HttpSessionBindingListener, Serializable {

    private static final long serialVersionUID = 1L;
    private String username; 
    private ActiveUserStore activeUserStore;
    
    public LoggedUser(String username, ActiveUserStore activeUserStore) {
        this.username = username;
        this.activeUserStore = activeUserStore;
    }
    
    public LoggedUser() {}

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        Collection<String> users = activeUserStore.getUsers();
        LoggedUser loggedUser = (LoggedUser) event.getValue();
        if (!users.contains(loggedUser.getUsername())) {
            users.add(loggedUser.getUsername());
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        Collection<String> users = activeUserStore.getUsers();
        LoggedUser loggedUser = (LoggedUser) event.getValue();
        if (users.contains(loggedUser.getUsername())) {
            users.remove(loggedUser.getUsername());
        }
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
