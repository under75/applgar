package ru.sartfoms.applgar.model;

public class House {
	private Integer id;
	private Integer objectId;
	private String objectguid;
	private String houseNum;
	private String addNum1;
	private String addNum2;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getObjectId() {
		return objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public String getObjectguid() {
		return objectguid;
	}

	public void setObjectguid(String objectguid) {
		this.objectguid = objectguid;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getAddNum1() {
		return addNum1;
	}

	public void setAddNum1(String addNum1) {
		this.addNum1 = addNum1;
	}

	public String getAddNum2() {
		return addNum2;
	}

	public void setAddNum2(String addNum2) {
		this.addNum2 = addNum2;
	}
}
