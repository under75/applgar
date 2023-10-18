package ru.sartfoms.applgar.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "mpi_policy", schema = "ASY23")
@IdClass(CompositeKey.class)
public class Policy {
	@Id
	@Column(name = "rid")
	private Long rid;

	@Id
	@Column(name = "nr")
	private Integer nr;

	@Column(name = "pcyser")
	private String pcySer;

	@Column(name = "pcynum")
	private String pcyNum;

	@Column(name = "enpcalc")
	private String enpCalc;

	@Column(name = "enp")
	private String enp;

	@Column(name = "pcydateb")
	private LocalDate pcyDateB;

	@Column(name = "pcydatee")
	private LocalDate pcyDateE;

	@Column(name = "pcydateh")
	private LocalDate pcyDateH;

	@Column(name = "pcydatet")
	private LocalDate pcyDateT;

	@Column(name = "pcydateenpcalc")
	private LocalDate pcyDateEnpCalc;

	@Column(name = "pcydatepr")
	private LocalDate pcyDatePr;

	@Column(name = "pcytype")
	private String pcyType;

	@Column(name = "pcystatus")
	private String pcyStatus;

	@OneToOne
	@JoinColumn(name = "okato", referencedColumnName = "cod")
	private Okato okato;

	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "dsource", referencedColumnName = "cod", insertable = false, updatable = false)
	private Okato dsource;

	@Column(name = "dsource")
	private String dsourceStr;

	@Column(name = "dsourcetype")
	private String dsourceType;

	@Column(name = "descr")
	private String descr;

	@Column(name = "sex")
	private Integer gender;

	@Column(name = "insurname")
	private String insurName;

	@Column(name = "insurfname")
	private String insurfName;

	@Column(name = "insurogrn")
	private String insurOgrn;

	@Column(name = "insurfogrn")
	private String insurfOgrn;

	@Column(name = "insurcode")
	private String insurCode;

	@Column(name = "insurfcode")
	private String insurfCode;

	@Column(name = "insurfdate")
	private LocalDate insurfDate;

	@Column(name = "tmpcertnum")
	private String tmpcertNum;

	@Column(name = "tmpcertdateb")
	private LocalDate tmpcertDateB;

	@Column(name = "tmpcertdatee")
	private LocalDate tmpcertDateE;

	@Column(name = "ueknum")
	private String uekNum;

	@OneToOne
	@JoinColumn(name = "pcycategory", referencedColumnName = "idkat")
	private Category pcyCategory;

	@Column(name = "fam")
	private String lastName;

	@Column(name = "ot")
	private String patronymic;

	@Column(name = "im")
	private String firstName;

	@Column(name = "dr")
	private LocalDate birthDay;

	@Column(name = "blanknum")
	private String blankNum;

	public Policy() {
	}

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public Integer getNr() {
		return nr;
	}

	public void setNr(Integer nr) {
		this.nr = nr;
	}

	public String getPcySer() {
		return pcySer;
	}

	public void setPcySer(String pcySer) {
		this.pcySer = pcySer;
	}

	public String getPcyNum() {
		return pcyNum;
	}

	public void setPcyNum(String pcyNum) {
		this.pcyNum = pcyNum;
	}

	public String getEnpCalc() {
		return enpCalc;
	}

	public void setEnpCalc(String enpCalc) {
		this.enpCalc = enpCalc;
	}

	public String getEnp() {
		return enp;
	}

	public void setEnp(String enp) {
		this.enp = enp;
	}

	public LocalDate getPcyDateB() {
		return pcyDateB;
	}

	public void setPcyDateB(LocalDate pcyDateB) {
		this.pcyDateB = pcyDateB;
	}

	public LocalDate getPcyDateE() {
		return pcyDateE;
	}

	public void setPcyDateE(LocalDate pcyDateE) {
		this.pcyDateE = pcyDateE;
	}

	public LocalDate getPcyDateH() {
		return pcyDateH;
	}

	public void setPcyDateH(LocalDate pcyDateH) {
		this.pcyDateH = pcyDateH;
	}

	public LocalDate getPcyDateT() {
		return pcyDateT;
	}

	public void setPcyDateT(LocalDate pcyDateT) {
		this.pcyDateT = pcyDateT;
	}

	public LocalDate getPcyDateEnpCalc() {
		return pcyDateEnpCalc;
	}

	public void setPcyDateEnpCalc(LocalDate pcyDateEnpCalc) {
		this.pcyDateEnpCalc = pcyDateEnpCalc;
	}

	public LocalDate getPcyDatePr() {
		return pcyDatePr;
	}

	public void setPcyDatePr(LocalDate pcyDatePr) {
		this.pcyDatePr = pcyDatePr;
	}

	public void setInsurfDate(LocalDate insurfDate) {
		this.insurfDate = insurfDate;
	}

	public void setTmpcertDateB(LocalDate tmpcertDateB) {
		this.tmpcertDateB = tmpcertDateB;
	}

	public void setTmpcertDateE(LocalDate tmpcertDateE) {
		this.tmpcertDateE = tmpcertDateE;
	}

	public void setPcyCategory(Category pcyCategory) {
		this.pcyCategory = pcyCategory;
	}

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
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

	public Okato getOkato() {
		return okato;
	}

	public void setOkato(Okato okato) {
		this.okato = okato;
	}

	public Okato getDsource() {
		return dsource;
	}

	public void setDsource(Okato dsource) {
		this.dsource = dsource;
	}

	public String getDsourceStr() {
		return dsourceStr;
	}

	public void setDsourceStr(String dsourceStr) {
		this.dsourceStr = dsourceStr;
	}

	public String getDsourceType() {
		return dsourceType;
	}

	public void setDsourceType(String dsourceType) {
		this.dsourceType = dsourceType;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getInsurName() {
		return insurName;
	}

	public void setInsurName(String insurName) {
		this.insurName = insurName;
	}

	public String getInsurfName() {
		return insurfName;
	}

	public void setInsurfName(String insurfName) {
		this.insurfName = insurfName;
	}

	public String getInsurOgrn() {
		return insurOgrn;
	}

	public void setInsurOgrn(String insurOgrn) {
		this.insurOgrn = insurOgrn;
	}

	public String getInsurfOgrn() {
		return insurfOgrn;
	}

	public void setInsurfOgrn(String insurfOgrn) {
		this.insurfOgrn = insurfOgrn;
	}

	public String getInsurCode() {
		return insurCode;
	}

	public void setInsurCode(String insurCode) {
		this.insurCode = insurCode;
	}

	public String getInsurfCode() {
		return insurfCode;
	}

	public void setInsurfCode(String insurfCode) {
		this.insurfCode = insurfCode;
	}

	public String getTmpcertNum() {
		return tmpcertNum;
	}

	public void setTmpcertNum(String tmpcertNum) {
		this.tmpcertNum = tmpcertNum;
	}

	public String getUekNum() {
		return uekNum;
	}

	public void setUekNum(String uekNum) {
		this.uekNum = uekNum;
	}

	public Category getPcyCategory() {
		return pcyCategory;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public LocalDate getInsurfDate() {
		return insurfDate;
	}

	public LocalDate getTmpcertDateB() {
		return tmpcertDateB;
	}

	public LocalDate getTmpcertDateE() {
		return tmpcertDateE;
	}

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public String getBlankNum() {
		return blankNum;
	}

	public void setBlankNum(String blankNum) {
		this.blankNum = blankNum;
	}

}
