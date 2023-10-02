package ru.sartfoms.applgar.entity;

import java.io.Serializable;
import java.util.Objects;

public class RoleId implements Serializable {
	private static final long serialVersionUID = 1L;

	private String u_name;

	private String role_name;
	
	public RoleId() {
	}

	public RoleId(String u_name, String role_name) {
		super();
		this.u_name = u_name;
		this.role_name = role_name;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(role_name, u_name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleId other = (RoleId) obj;
		return Objects.equals(role_name, other.role_name) && Objects.equals(u_name, other.u_name);
	}
	
}

