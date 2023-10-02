package ru.sartfoms.applgar.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INSPECTOR", schema = "OMCOWNER")
public class Inspector {
	@Id
	@Column(name = "cd_insp")
	private Long cdInsp;
	
	@Column(name = "fio_insp")
	private String fioInsp;
	
	@Column(name = "cd_smo")
	private Integer cdSmo;
	
	@Column(name = "cd_fsmo")
	private Integer cdFsmo;
	
	@Column(name = "login")
	private String login;
	
	public Inspector() {
	}

	public Inspector(Long cdInsp, String fioInsp, Integer cdSmo, Integer cdFsmo) {
		super();
		this.cdInsp = cdInsp;
		this.fioInsp = fioInsp;
		this.cdSmo = cdSmo;
		this.cdFsmo = cdFsmo;
	}

	public Long getCdInsp() {
		return cdInsp;
	}

	public void setCdInsp(Long cdInsp) {
		this.cdInsp = cdInsp;
	}

	public String getFioInsp() {
		return fioInsp;
	}

	public void setFioInsp(String fioInsp) {
		this.fioInsp = fioInsp;
	}

	public Integer getCdSmo() {
		return cdSmo;
	}

	public void setCdSmo(Integer cdSmo) {
		this.cdSmo = cdSmo;
	}

	public Integer getCdFsmo() {
		return cdFsmo;
	}

	public void setCdFsmo(Integer cdFsmo) {
		this.cdFsmo = cdFsmo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cdInsp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inspector other = (Inspector) obj;
		return Objects.equals(cdInsp, other.cdInsp);
	}
	
	
}

