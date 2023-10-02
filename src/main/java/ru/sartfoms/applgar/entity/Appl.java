package ru.sartfoms.applgar.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.thymeleaf.util.StringUtils;

@Entity
@Table(name = "V_APPL", schema = "OMCOWNER")
public class Appl {
	@Id
	@Column(name = "id_appl")
	private Long id_appl;

	@Column(name = "dt_appl")
	private LocalDate dtAppl;

	@Column(name = "id_mpi")
	private Long idMpi;

	@Column(name = "cd_smo")
	private Integer cdSmo;

	@Column(name = "cd_fsmo")
	private Integer cdFsmo;

	@OneToOne(cascade = CascadeType.PERSIST)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "cd_insp", referencedColumnName = "cd_insp")
	private Inspector inspector;

	@Column(name = "id_pers")
	private Long id_pers;

	@Column(name = "fam")
	private String fam;

	@Column(name = "im")
	private String im;

	@Column(name = "ot")
	private String ot;

	@Column(name = "dr")
	private LocalDate dr;

	@Column(name = "id_adrreg")
	private Long id_adrreg;

	@Column(name = "id_adrpr")
	private Long id_adrpr;

	@Column(name = "ser_doc")
	private String serDoc;

	@Column(name = "num_doc")
	private String numDoc;

	@Column(name = "gar_appl")
	private Long garAppl;
	
	@Column(name = "id_adrreg2")
	private Long id_adrreg2;

	@Column(name = "id_adrpr2")
	private Long id_adrpr2;
	
	@Column(name = "ser_doc2")
	private String serDoc2;

	@Column(name = "num_doc2")
	private String numDoc2;

	public Appl() {
	}

	public Long getId_appl() {
		return id_appl;
	}

	public void setId_appl(Long id_appl) {
		this.id_appl = id_appl;
	}

	public LocalDate getDtAppl() {
		return dtAppl;
	}

	public void setDtAppl(LocalDate dtAppl) {
		this.dtAppl = dtAppl;
	}

	public Long getIdMpi() {
		return idMpi;
	}

	public void setIdMpi(Long idMpi) {
		this.idMpi = idMpi;
	}

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


	public Inspector getInspector() {
		return inspector;
	}

	public void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}

	public Long getId_pers() {
		return id_pers;
	}

	public void setId_pers(Long id_pers) {
		this.id_pers = id_pers;
	}

	public String getFam() {
		return StringUtils.capitalize(fam.toLowerCase());
	}

	public void setFam(String fam) {
		this.fam = fam;
	}

	public String getIm() {
		return StringUtils.capitalize(im.toLowerCase());
	}

	public void setIm(String im) {
		this.im = im;
	}

	public String getOt() {
		return StringUtils.capitalize(ot.toLowerCase());
	}

	public void setOt(String ot) {
		this.ot = ot;
	}

	public LocalDate getDr() {
		return dr;
	}

	public void setDr(LocalDate dr) {
		this.dr = dr;
	}

	public Long getId_adrreg() {
		return id_adrreg;
	}

	public void setId_adrreg(Long id_adrreg) {
		this.id_adrreg = id_adrreg;
	}

	public Long getId_adrpr() {
		return id_adrpr;
	}

	public void setId_adrpr(Long id_adrpr) {
		this.id_adrpr = id_adrpr;
	}

	public String getSerDoc() {
		return serDoc;
	}

	public void setSerDoc(String serDoc) {
		this.serDoc = serDoc;
	}

	public String getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	public Long getGarAppl() {
		return garAppl;
	}

	public void setGarAppl(Long garAppl) {
		this.garAppl = garAppl;
	}

	public Long getId_adrreg2() {
		return id_adrreg2;
	}

	public void setId_adrreg2(Long id_adrreg2) {
		this.id_adrreg2 = id_adrreg2;
	}

	public Long getId_adrpr2() {
		return id_adrpr2;
	}

	public void setId_adrpr2(Long id_adrpr2) {
		this.id_adrpr2 = id_adrpr2;
	}
	
	public String getSerDoc2() {
		return serDoc2;
	}

	public void setSerDoc2(String serDoc2) {
		this.serDoc2 = serDoc2;
	}

	public String getNumDoc2() {
		return numDoc2;
	}

	public void setNumDoc2(String numDoc2) {
		this.numDoc2 = numDoc2;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_appl, id_pers);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appl other = (Appl) obj;
		return Objects.equals(id_appl, other.id_appl) && Objects.equals(id_pers, other.id_pers);
	}

}

