package com.gxl.entity;

// Generated 2016-12-16 22:20:10 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PArea generated by hbm2java
 */
@Entity
@Table(name = "p_area", catalog = "mydb")
public class PArea implements java.io.Serializable {

	private String areaCode;
	private String areaName;
	private String areaLevel;
	private String areaType;

	public PArea() {
	}

	public PArea(String areaCode, String areaName, String areaLevel,
			String areaType) {
		this.areaCode = areaCode;
		this.areaName = areaName;
		this.areaLevel = areaLevel;
		this.areaType = areaType;
	}

	@Id
	@Column(name = "area_code", unique = true, nullable = false, length = 12)
	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "area_name", nullable = false, length = 64)
	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Column(name = "area_level", nullable = false, length = 2)
	public String getAreaLevel() {
		return this.areaLevel;
	}

	public void setAreaLevel(String areaLevel) {
		this.areaLevel = areaLevel;
	}

	@Column(name = "area_type", nullable = false, length = 2)
	public String getAreaType() {
		return this.areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

}
