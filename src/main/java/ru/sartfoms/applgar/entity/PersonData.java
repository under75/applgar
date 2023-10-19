package ru.sartfoms.applgar.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.thymeleaf.util.StringUtils;

@Entity
@Table(name = "mpi_person_data", schema = "OMCOWNER")
public class PersonData {
	@Id
	@Column(name = "rid")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
	@SequenceGenerator(name = "id_sequence", sequenceName = "OMCOWNER.MPI_SEQ", allocationSize = 1)
	private Long rid;

	@Column(name = "hist")
	private Boolean historical;

	@Column(name = "oip")
	private String oip;

	@Column(name = "polis_type")
	private String pcyType;
	
	@Column(name = "pcyser")
	private String pcySer;

	@Column(name = "polis")
	private String pcy;

	@Column(name = "dudlser")
	private String dudlSer;

	@Column(name = "dudlnum")
	private String dudlNum;

//	@OneToOne
//	@NotFound(action = NotFoundAction.IGNORE)
//	@JoinColumn(name = "dudltype", referencedColumnName = "code",insertable=false, updatable=false)
//	private DudlType dudlType;
	
	@Column(name = "dudltype")
	private Integer dudlType;

	@Column(name = "snils")
	private String snils;

	@Column(name = "dr")
	private LocalDate birthDay;

	@Column(name = "fam")
	private String lastName;

	@Column(name = "im")
	private String firstName;

	@Column(name = "ot")
	private String patronymic;

	@Column(name = "dt")
	private LocalDate dt;

	@Column(name = "dtfrom")
	private LocalDate dtFrom;

	@Column(name = "dtto")
	private LocalDate dtTo;

	@Column(name = "showx")
	private String show;

	@Column(name = "usr")
	private String user;

	@Column(name = "dt_ins")
	private LocalDateTime dtIns;
	
	@Column(name = "err")
	private Boolean hasError;
	
	@Column(name = "oip_resp")
	private String oipRes;

	public PersonData() {
	}

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public Boolean getHistorical() {
		return historical;
	}

	public void setHistorical(Boolean historical) {
		this.historical = historical;
	}

	public String getOip() {
		return oip;
	}

	public void setOip(String oip) {
		this.oip = oip;
	}

	public String getPcyType() {
		return pcyType;
	}

	public void setPcyType(String pcyType) {
		this.pcyType = pcyType;
	}

	public String getPcySer() {
		return pcySer;
	}

	public void setPcySer(String pcySer) {
		this.pcySer = pcySer;
	}

	public String getPcy() {
		return pcy;
	}

	public void setPcy(String pcy) {
		this.pcy = pcy;
	}

	public String getDudlSer() {
		return dudlSer;
	}

	public void setDudlSer(String dudlSer) {
		if (dudlSer.matches("^\\d{4}$")) {
			dudlSer = dudlSer.substring(0, 2) + " " + dudlSer.substring(2);
		}
		this.dudlSer = dudlSer;
	}

	public String getDudlNum() {
		return dudlNum;
	}

	public void setDudlNum(String dudlNum) {
		this.dudlNum = dudlNum;
	}

	public Integer getDudlType() {
		return dudlType;
	}

	public void setDudlType(Integer dudlType) {
		this.dudlType = dudlType;
	}

	public String getSnils() {
		return snils;
	}

	public void setSnils(String snils) {
		this.snils = snils;
	}

	public String getLastName() {
		return StringUtils.capitalize(lastName != null ? lastName.toLowerCase() : "");
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return StringUtils.capitalize(firstName != null ? firstName.toLowerCase() : "");
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPatronymic() {
		return StringUtils.capitalize(patronymic != null ? patronymic.toLowerCase() : "");
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Boolean getHasError() {
		return hasError;
	}

	public void setHasError(Boolean hasError) {
		this.hasError = hasError;
	}

	public String getOipRes() {
		return oipRes;
	}

	public void setOipRes(String oipRes) {
		this.oipRes = oipRes;
	}

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}

	public LocalDate getDt() {
		return dt;
	}

	public void setDt(LocalDate dt) {
		this.dt = dt;
	}

	public LocalDate getDtFrom() {
		return dtFrom;
	}

	public void setDtFrom(LocalDate dtFrom) {
		this.dtFrom = dtFrom;
	}

	public LocalDate getDtTo() {
		return dtTo;
	}

	public void setDtTo(LocalDate dtTo) {
		this.dtTo = dtTo;
	}

	public LocalDateTime getDtIns() {
		return dtIns;
	}

	public void setDtIns(LocalDateTime dtIns) {
		this.dtIns = dtIns;
	}

}
