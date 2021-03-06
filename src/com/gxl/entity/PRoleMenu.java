package com.gxl.entity;

// Generated 2016-10-18 12:09:23 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PRoleMenu generated by hbm2java
 */
@Entity
@Table(name = "p_role_menu", catalog = "mydb")
public class PRoleMenu implements java.io.Serializable {

	private String rmId;
	private String roleId;
	private String menuId;

	public PRoleMenu() {
	}

	public PRoleMenu(String rmId, String roleId, String menuId) {
		this.rmId = rmId;
		this.roleId = roleId;
		this.menuId = menuId;
	}

	@Id
	@Column(name = "rm_id", unique = true, nullable = false, length = 32)
	public String getRmId() {
		return this.rmId;
	}

	public void setRmId(String rmId) {
		this.rmId = rmId;
	}

	@Column(name = "role_id", nullable = false, length = 32)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "menu_id", nullable = false, length = 32)
	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}
