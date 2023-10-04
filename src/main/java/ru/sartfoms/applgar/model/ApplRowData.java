package ru.sartfoms.applgar.model;

import java.time.LocalDate;

import org.thymeleaf.util.StringUtils;

public class ApplRowData {
	private String num;

	private String smoCode;

	private String fsmoCode;

	private String fsmoName;

	private LocalDate applDate;

	private String applType;

	private String applCause;

	private String personLastName;

	private String personFirstName;

	private String personPatronymic;

	private LocalDate personBirsday;

	private String personGender;

	private String personHomePhone;

	private String personWorkPhone;

	private String personEmail;

	private String representativeHomePhone;

	private String representativeWorkPhone;

	private String representativeEmail;

	private String inspectorCode;

	private String inspectorFullName;

	private String personAddressReg;

	private String personAddressPr;

	private String personPolisNumber;

	private String rguidreg;

	private String aoguidreg;

	private String hsguidreg;

	private String rguidpr;

	private String aoguidpr;

	private String hsguidpr;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getSmoCode() {
		return smoCode;
	}

	public void setSmoCode(String smoCode) {
		this.smoCode = smoCode;
	}

	public String getFsmoCode() {
		return fsmoCode;
	}

	public void setFsmoCode(String fsmoCode) {
		this.fsmoCode = fsmoCode;
	}

	public String getFsmoName() {
		return fsmoName;
	}

	public void setFsmoName(String fsmoName) {
		this.fsmoName = fsmoName;
	}

	public LocalDate getApplDate() {
		return applDate;
	}

	public void setApplDate(LocalDate applDate) {
		this.applDate = applDate;
	}

	public String getApplType() {
		return applType;
	}

	public void setApplType(String applType) {
		this.applType = applType;
	}

	public String getApplCause() {
		return applCause;
	}

	public void setApplCause(String applCause) {
		this.applCause = applCause;
	}

	public String getPersonLastName() {
		return StringUtils.capitalize(personLastName.toLowerCase());
	}

	public void setPersonLastName(String personLastName) {
		this.personLastName = personLastName;
	}

	public String getPersonFirstName() {
		return StringUtils.capitalize(personFirstName.toLowerCase());
	}

	public void setPersonFirstName(String personFirstName) {
		this.personFirstName = personFirstName;
	}

	public String getPersonPatronymic() {
		return StringUtils.capitalize(personPatronymic.toLowerCase());
	}

	public void setPersonPatronymic(String personPatronymic) {
		this.personPatronymic = personPatronymic;
	}

	public LocalDate getPersonBirsday() {
		return personBirsday;
	}

	public void setPersonBirsday(LocalDate personBirsday) {
		this.personBirsday = personBirsday;
	}

	public String getPersonGender() {
		return personGender;
	}

	public void setPersonGender(String personGender) {
		this.personGender = personGender;
	}

	public String getPersonHomePhone() {
		return personHomePhone;
	}

	public void setPersonHomePhone(String personHomePhone) {
		this.personHomePhone = personHomePhone;
	}

	public String getPersonWorkPhone() {
		return personWorkPhone;
	}

	public void setPersonWorkPhone(String personWorkPhone) {
		this.personWorkPhone = personWorkPhone;
	}

	public String getPersonEmail() {
		return personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	public String getRepresentativeHomePhone() {
		return representativeHomePhone;
	}

	public void setRepresentativeHomePhone(String representativeHomePhone) {
		this.representativeHomePhone = representativeHomePhone;
	}

	public String getRepresentativeWorkPhone() {
		return representativeWorkPhone;
	}

	public void setRepresentativeWorkPhone(String representativeWorkPhone) {
		this.representativeWorkPhone = representativeWorkPhone;
	}

	public String getRepresentativeEmail() {
		return representativeEmail;
	}

	public void setRepresentativeEmail(String representativeEmail) {
		this.representativeEmail = representativeEmail;
	}

	public String getInspectorCode() {
		return inspectorCode;
	}

	public void setInspectorCode(String inspectorCode) {
		this.inspectorCode = inspectorCode;
	}

	public String getInspectorFullName() {
		return inspectorFullName;
	}

	public void setInspectorFullName(String inspectorFullName) {
		this.inspectorFullName = inspectorFullName;
	}

	public String getPersonAddressReg() {
		return personAddressReg;
	}

	public void setPersonAddressReg(String personAddressReg) {
		this.personAddressReg = personAddressReg;
	}

	public String getPersonAddressPr() {
		return personAddressPr;
	}

	public void setPersonAddressPr(String personAddressPr) {
		this.personAddressPr = personAddressPr;
	}

	public String getPersonPolisNumber() {
		return personPolisNumber;
	}

	public void setPersonPolisNumber(String personPolisNumber) {
		this.personPolisNumber = personPolisNumber;
	}

	public String getRguidreg() {
		return rguidreg;
	}

	public void setRguidreg(String rguidreg) {
		this.rguidreg = rguidreg;
	}

	public String getAoguidreg() {
		return aoguidreg;
	}

	public void setAoguidreg(String aoguidreg) {
		this.aoguidreg = aoguidreg;
	}

	public String getHsguidreg() {
		return hsguidreg;
	}

	public void setHsguidreg(String hsguidreg) {
		this.hsguidreg = hsguidreg;
	}

	public String getRguidpr() {
		return rguidpr;
	}

	public void setRguidpr(String rguidpr) {
		this.rguidpr = rguidpr;
	}

	public String getAoguidpr() {
		return aoguidpr;
	}

	public void setAoguidpr(String aoguidpr) {
		this.aoguidpr = aoguidpr;
	}

	public String getHsguidpr() {
		return hsguidpr;
	}

	public void setHsguidpr(String hsguidpr) {
		this.hsguidpr = hsguidpr;
	}

}
