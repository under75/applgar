package ru.sartfoms.applgar.model;

import java.util.Collection;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PolicySearchParameters {
	private String policyType;
	@Size(max=10)
	private String policySer;
	@Size(max=16)
	private String policyNum;
	private Integer dudlType;
	@Size(max=12)
	private String dudlSer;
	@Size(max=20)
	private String dudlNum;
	@Size(max=14)
	private String snils;
	@NotEmpty
	private String birthDay;
	@Size(min=2,max=40)
	private String lastName;
	@Size(min=2,max=40)
	private String firstName;
	private String patronymic;
	private Boolean historical = false;
	private String dt;
	private String dtFrom;
	private String dtTo;
	private String dateFrom;
	private String dateTo;
	private Collection<Long> selectedRows;
	
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public String getPolicySer() {
		return policySer;
	}
	public void setPolicySer(String policySer) {
		this.policySer = policySer;
	}
	public String getPolicyNum() {
		return policyNum;
	}
	public void setPolicyNum(String policyNum) {
		this.policyNum = policyNum;
	}
	public Integer getDudlType() {
		return dudlType;
	}
	public void setDudlType(Integer dudlType) {
		this.dudlType = dudlType;
	}
	public String getDudlSer() {
		return dudlSer;
	}
	public void setDudlSer(String dudlSer) {
		this.dudlSer = dudlSer;
	}
	public String getDudlNum() {
		return dudlNum;
	}
	public void setDudlNum(String dudlNum) {
		this.dudlNum = dudlNum;
	}
	public String getSnils() {
		return snils;
	}
	public void setSnils(String snils) {
		this.snils = snils;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getPatronymic() {
		return patronymic;
	}
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	public Boolean getHistorical() {
		return historical;
	}
	public void setHistorical(Boolean historical) {
		this.historical = historical;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getDtFrom() {
		return dtFrom;
	}
	public void setDtFrom(String dtFrom) {
		this.dtFrom = dtFrom;
	}
	public String getDtTo() {
		return dtTo;
	}
	public void setDtTo(String dtTo) {
		this.dtTo = dtTo;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public Collection<Long> getSelectedRows() {
		return selectedRows;
	}
	public void setSelectedRows(Collection<Long> selectedRows) {
		this.selectedRows = selectedRows;
	}
}
