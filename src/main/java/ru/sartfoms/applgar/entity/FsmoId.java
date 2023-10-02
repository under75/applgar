package ru.sartfoms.applgar.entity;

import java.io.Serializable;
import java.util.Objects;

public class FsmoId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer cdSmo;
	
	private Integer cdFsmo;
	
	public FsmoId() {
	}

	public FsmoId(Integer cdSmo, Integer cdFsmo) {
		super();
		this.cdSmo = cdSmo;
		this.cdFsmo = cdFsmo;
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

	@Override
	public int hashCode() {
		return Objects.hash(cdFsmo, cdSmo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FsmoId other = (FsmoId) obj;
		return Objects.equals(cdFsmo, other.cdFsmo) && Objects.equals(cdSmo, other.cdSmo);
	}
	
}

