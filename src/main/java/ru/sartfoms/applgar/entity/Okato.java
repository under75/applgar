package ru.sartfoms.applgar.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "okatoter", schema = "OMCOWNER")
public class Okato implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cod")
	private String code;
	
	@Column(name = "naim")
	private String title;
	
	@Column(name = "centrum")
	private String centrum;
	
	@Column(name = "codtfoms")
	private String codTfoms;
	
	@Column(name = "prz")
	private Integer prz;
	
	public Okato() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCentrum() {
		return centrum;
	}

	public void setCentrum(String centrum) {
		this.centrum = centrum;
	}

	public String getCodTfoms() {
		return codTfoms;
	}

	public void setCodTfoms(String codTfoms) {
		this.codTfoms = codTfoms;
	}

	public Integer getPrz() {
		return prz;
	}

	public void setPrz(Integer prz) {
		this.prz = prz;
	}
}
