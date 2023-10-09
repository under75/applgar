package ru.sartfoms.applgar.model;

import java.time.LocalDateTime;

public class UserDTO {
	private String id;
	
	private LocalDateTime effDate;

	private String passwd;

	private Integer smo;

	private Integer fSmo;

	private String lastname;

	private String firstname;

	private String patronymic;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public LocalDateTime getEffDate() {
		return effDate;
	}

	public void setEffDate(LocalDateTime effDate) {
		this.effDate = effDate;
	}
	
}
