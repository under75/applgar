package ru.sartfoms.applgar.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "jpol_users", schema = "BIGADMIN")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "u_name")
	private String name;

	@Column(name = "u_hash")
	private String passwd;

	@Column(name = "u_smo")
	private Integer smo;

	@Column(name = "u_fsmo")
	private Integer fSmo;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "u_name", referencedColumnName = "u_name")
	private Collection<Role> roles = new ArrayList<>();

	public User() {
	}

	public User(String name, String passwd, Integer smo, Integer fSmo, Collection<Role> roles) {
		super();
		this.name = name;
		this.passwd = passwd;
		setSmo(smo);
		this.fSmo = fSmo;
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Integer getSmo() {
		return smo;
	}

	public void setSmo(Integer smo) {
		this.smo = smo;
	}

	public Integer getfSmo() {
		return fSmo;
	}

	public void setfSmo(Integer fSmo) {
		this.fSmo = fSmo;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, passwd);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(name, other.name) && Objects.equals(passwd, other.passwd);
	}
}
