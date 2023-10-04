package ru.sartfoms.applgar.model;

import java.time.LocalDate;

import org.springframework.util.StringUtils;

public class PolicyRowData {

	private String enp;

	private LocalDate pcyEffDt;

	private String pcyType;

	private String pcyStatus;

	private String dsourceType;

	private String gender;

	private String lastName;

	private String firstName;

	private String patronymic;

	private LocalDate birthDay;

	private String blankNum;

	private LocalDate pcyExpDt;

	public String getEnp() {
		return enp;
	}

	public void setEnp(String enp) {
		this.enp = enp;
	}

	public LocalDate getPcyEffDt() {
		return pcyEffDt;
	}

	public void setPcyEffDt(LocalDate pcyEffDt) {
		this.pcyEffDt = pcyEffDt;
	}

	public String getPcyType() {
		return pcyType;
	}

	public void setPcyType(String pcyType) {
		this.pcyType = pcyType;
	}

	public String getPcyStatus() {
		return pcyStatus;
	}

	public void setPcyStatus(String pcyStatus) {
		this.pcyStatus = pcyStatus;
	}

	public String getDsourceType() {
		return dsourceType;
	}

	public void setDsourceType(String dsourceType) {
		this.dsourceType = dsourceType;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastName() {
		return StringUtils.capitalize(lastName.toLowerCase());
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return StringUtils.capitalize(firstName.toLowerCase());
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPatronymic() {
		return StringUtils.capitalize(patronymic.toLowerCase());
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}

	public String getBlankNum() {
		return blankNum;
	}

	public void setBlankNum(String blankNum) {
		this.blankNum = blankNum;
	}

	public LocalDate getPcyExpDt() {
		return pcyExpDt;
	}

	public void setPcyExpDt(LocalDate pcyExpDt) {
		this.pcyExpDt = pcyExpDt;
	}

}
