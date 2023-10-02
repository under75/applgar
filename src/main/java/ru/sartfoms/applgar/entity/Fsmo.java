package ru.sartfoms.applgar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "smo_fil", schema = "OMCOWNER")
@IdClass(FsmoId.class)
public class Fsmo {
	@Id
	@Column(name = "CD_SMO")
	private Integer cdSmo;
	@Id
	@Column(name = "CD_FSMO")
	private Integer cdFsmo;
	
	@Column(name = "NM_FSMO")
	private String name;
	
	@Column(name = "ADR")
	private String address;
	
	@Column(name = "PHONE")
	private String phone;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}

