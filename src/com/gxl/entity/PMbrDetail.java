package com.gxl.entity;

// Generated 2016-12-16 22:20:10 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PMbrDetail generated by hbm2java
 */
@Entity
@Table(name = "p_mbr_detail", catalog = "mydb")
public class PMbrDetail implements java.io.Serializable {

	private String mbrId;
	private String needChild;
	private String canYidi;
	private String likeType;
	private String canSex;
	private String withParents;
	private String bestPart;
	private String hobbys;
	private String characters;
	private String soliloquy;

	public PMbrDetail() {
	}

	public PMbrDetail(String mbrId) {
		this.mbrId = mbrId;
	}

	public PMbrDetail(String mbrId, String needChild, String canYidi,
			String likeType, String canSex, String withParents,
			String bestPart, String hobbys, String characters, String soliloquy) {
		this.mbrId = mbrId;
		this.needChild = needChild;
		this.canYidi = canYidi;
		this.likeType = likeType;
		this.canSex = canSex;
		this.withParents = withParents;
		this.bestPart = bestPart;
		this.hobbys = hobbys;
		this.characters = characters;
		this.soliloquy = soliloquy;
	}

	@Id
	@Column(name = "mbr_id", unique = true, nullable = false, length = 32)
	public String getMbrId() {
		return this.mbrId;
	}

	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}

	@Column(name = "need_child", length = 2)
	public String getNeedChild() {
		return this.needChild;
	}

	public void setNeedChild(String needChild) {
		this.needChild = needChild;
	}

	@Column(name = "can_yidi", length = 2)
	public String getCanYidi() {
		return this.canYidi;
	}

	public void setCanYidi(String canYidi) {
		this.canYidi = canYidi;
	}

	@Column(name = "like_type", length = 2)
	public String getLikeType() {
		return this.likeType;
	}

	public void setLikeType(String likeType) {
		this.likeType = likeType;
	}

	@Column(name = "can_sex", length = 2)
	public String getCanSex() {
		return this.canSex;
	}

	public void setCanSex(String canSex) {
		this.canSex = canSex;
	}

	@Column(name = "with_parents", length = 2)
	public String getWithParents() {
		return this.withParents;
	}

	public void setWithParents(String withParents) {
		this.withParents = withParents;
	}

	@Column(name = "best_part", length = 2)
	public String getBestPart() {
		return this.bestPart;
	}

	public void setBestPart(String bestPart) {
		this.bestPart = bestPart;
	}

	@Column(name = "hobbys", length = 128)
	public String getHobbys() {
		return this.hobbys;
	}

	public void setHobbys(String hobbys) {
		this.hobbys = hobbys;
	}

	@Column(name = "characters", length = 128)
	public String getCharacters() {
		return this.characters;
	}

	public void setCharacters(String characters) {
		this.characters = characters;
	}

	@Column(name = "soliloquy", length = 254)
	public String getSoliloquy() {
		return this.soliloquy;
	}

	public void setSoliloquy(String soliloquy) {
		this.soliloquy = soliloquy;
	}

}
