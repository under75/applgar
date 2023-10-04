package ru.sartfoms.applgar.model;

import javax.validation.constraints.Size;

public class AncessorOipParameters {
	@Size(max=12, min=12)
	private String oip;
	private String dateFrom;
	private String dateTo;

	public String getOip() {
		return oip;
	}

	public void setOip(String oip) {
		this.oip = oip.trim();
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	
}
