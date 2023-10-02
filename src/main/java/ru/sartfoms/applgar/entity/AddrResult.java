package ru.sartfoms.applgar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS_GAR", schema = "OMCOWNER")
public class AddrResult {
	@Id
	@Column(name = "id_appl")
	private Long id_appl;
	
	@Column(name = "rguidreg")
	private String rguidreg;

	@Column(name = "aoguidreg")
	private String aoguidreg;
	
	@Column(name = "hsguidreg")
	private String hsguidreg;
	
	@Column(name = "rguidpr")
	private String rguidpr;
	
	@Column(name = "aoguidpr")
	private String aoguidpr;
	
	@Column(name = "hsguidpr")
	private String hsguidpr;
	
	public Long getId_appl() {
		return id_appl;
	}
	public void setId_appl(Long id_appl) {
		this.id_appl = id_appl;
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

