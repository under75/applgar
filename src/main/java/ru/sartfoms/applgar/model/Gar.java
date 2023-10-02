package ru.sartfoms.applgar.model;

import java.util.Collection;

public class Gar {
	private Collection<Address> level1Reg;
	private Collection<Address> level2Reg;
	private Collection<Address> level3Reg;
	private Collection<Address> level31Reg;
	private Collection<House> level4Reg;

	private Collection<Address> level1Pr;
	private Collection<Address> level2Pr;
	private Collection<Address> level3Pr;
	private Collection<Address> level31Pr;
	private Collection<House> level4Pr;

	public Collection<Address> getLevel1Reg() {
		return level1Reg;
	}

	public void setLevel1Reg(Collection<Address> level1Reg) {
		this.level1Reg = level1Reg;
	}

	public Collection<Address> getLevel2Reg() {
		return level2Reg;
	}

	public void setLevel2Reg(Collection<Address> level2Reg) {
		this.level2Reg = level2Reg;
	}

	public Collection<Address> getLevel3Reg() {
		return level3Reg;
	}

	public void setLevel3Reg(Collection<Address> level3Reg) {
		this.level3Reg = level3Reg;
	}

	public Collection<House> getLevel4Reg() {
		return level4Reg;
	}

	public void setLevel4Reg(Collection<House> level4Reg) {
		this.level4Reg = level4Reg;
	}

	public Collection<Address> getLevel1Pr() {
		return level1Pr;
	}

	public void setLevel1Pr(Collection<Address> level1Pr) {
		this.level1Pr = level1Pr;
	}

	public Collection<Address> getLevel2Pr() {
		return level2Pr;
	}

	public void setLevel2Pr(Collection<Address> level2Pr) {
		this.level2Pr = level2Pr;
	}

	public Collection<Address> getLevel3Pr() {
		return level3Pr;
	}

	public void setLevel3Pr(Collection<Address> level3Pr) {
		this.level3Pr = level3Pr;
	}

	public Collection<House> getLevel4Pr() {
		return level4Pr;
	}

	public void setLevel4Pr(Collection<House> level4Pr) {
		this.level4Pr = level4Pr;
	}

	public Collection<Address> getLevel31Reg() {
		return level31Reg;
	}

	public void setLevel31Reg(Collection<Address> level31Reg) {
		this.level31Reg = level31Reg;
	}

	public Collection<Address> getLevel31Pr() {
		return level31Pr;
	}

	public void setLevel31Pr(Collection<Address> level31Pr) {
		this.level31Pr = level31Pr;
	}
}

