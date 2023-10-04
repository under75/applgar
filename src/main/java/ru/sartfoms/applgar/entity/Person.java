package ru.sartfoms.applgar.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mpi_person", schema = "OMCOWNER")
@IdClass(CompositeKey.class)
public class Person {
	@Id
	@Column(name = "rid")
	private Long rid;

	@Id
	@Column(name = "nr")
	private Integer nr;

	@Column(name = "im")
	private String firstName;

	@Column(name = "fam")
	private String lastName;

	@Column(name = "ot")
	private String patronymic;

	@Column(name = "dr")
	private LocalDate birthDay;

	@Column(name = "sex")
	private Integer gender;

	@OneToOne
	@JoinColumn(name = "birth_oksm", referencedColumnName = "alfa3")
	private Oksm birthOksm;
	
	@Column(name = "death_dt")
	private LocalDate deathDate;

	@Column(name = "when_merged")
	private LocalDateTime whenMerged;
	
	@Column(name = "date_edit")
	private LocalDateTime dateEdit;
	
	@Column(name = "status")
	private String status;
	
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Oksm getBirthOksm() {
		return birthOksm;
	}

	public void setBirthOksm(Oksm birthOksm) {
		this.birthOksm = birthOksm;
	}

	public LocalDate getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(LocalDate deathDate) {
		this.deathDate = deathDate;
	}

	public LocalDateTime getWhenMerged() {
		return whenMerged;
	}

	public LocalDateTime getDateEdit() {
		return dateEdit;
	}

	public String getStatus() {
		return status;
	}

	public void setWhenMerged(LocalDateTime whenMerged) {
		this.whenMerged = whenMerged;
	}

	public void setDateEdit(LocalDateTime dateEdit) {
		this.dateEdit = dateEdit;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
