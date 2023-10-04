package ru.sartfoms.applgar.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oksm", schema = "OMCOWNER")
public class Oksm implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cod")
	private String code;
	
	@Column(name = "name1")
	private String name1;
	
	@Column(name = "name2")
	private String name2;
	
	@Column(name = "alfa2")
	private String alfa2;
	
	@Column(name = "alfa3")
	private String alfa3;
	
	@Column(name = "status")
	private Integer status;

	public Oksm() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getAlfa2() {
		return alfa2;
	}

	public void setAlfa2(String alfa2) {
		this.alfa2 = alfa2;
	}

	public String getAlfa3() {
		return alfa3;
	}

	public void setAlfa3(String alfa3) {
		this.alfa3 = alfa3;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}

