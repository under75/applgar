package ru.sartfoms.applgar.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MPI_GET_MERGE_ANCESSOR_OIP", schema = "ASY23")
public class MergeAncessorOip {
	@Id
	@Column(name = "rid")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
	@SequenceGenerator(name = "id_sequence", sequenceName = "ASY23.MPI_SEQ", allocationSize = 1)
	private Long rid;

	@Column(name = "usr")
	private String user;

	@Column(name = "dt_ins")
	private LocalDateTime dtIns;

	@Column(name = "err")
	private Boolean hasError;

	@Column(name = "oip")
	private String oip;

	@Column(name = "merge_ancessor_oip")
	private String mergeAncessorOip;

	@Column(name = "when_merged")
	private LocalDateTime whenMerged;

	public Long getRid() {
		return rid;
	}

	public String getUser() {
		return user;
	}

	public LocalDateTime getDtIns() {
		return dtIns;
	}

	public Boolean getHasError() {
		return hasError;
	}

	public String getOip() {
		return oip;
	}

	public String getMergeAncessorOip() {
		return mergeAncessorOip;
	}

	public LocalDateTime getWhenMerged() {
		return whenMerged;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setDtIns(LocalDateTime dtIns) {
		this.dtIns = dtIns;
	}

	public void setHasError(Boolean hasError) {
		this.hasError = hasError;
	}

	public void setOip(String oip) {
		this.oip = oip;
	}

	public void setMergeAncessorOip(String mergeAncessorOip) {
		this.mergeAncessorOip = mergeAncessorOip;
	}

	public void setWhenMerged(LocalDateTime whenMerged) {
		this.whenMerged = whenMerged;
	}

}
