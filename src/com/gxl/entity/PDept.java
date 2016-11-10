package com.gxl.entity;

// Generated 2016-10-27 20:45:06 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PDept generated by hbm2java
 */
@Entity
@Table(name = "p_dept", catalog = "mydb")
public class PDept implements java.io.Serializable {

	private String deptId;
	private String deptName;
	private String deptDesc;
	private String deptLevel;
	private String deptMngId;
	private String upDeptId;
	private String deptState;
	private String createrId;
	private String createTime;
	private String updaterId;
	private String updateTime;

	public PDept() {
	}

	public PDept(String deptId, String deptName, String deptLevel,
			String deptState, String createrId, String createTime) {
		this.deptId = deptId;
		this.deptName = deptName;
		this.deptLevel = deptLevel;
		this.deptState = deptState;
		this.createrId = createrId;
		this.createTime = createTime;
	}

	public PDept(String deptId, String deptName, String deptDesc,
			String deptLevel, String deptMngId, String upDeptId,
			String deptState, String createrId, String createTime,
			String updaterId, String updateTime) {
		this.deptId = deptId;
		this.deptName = deptName;
		this.deptDesc = deptDesc;
		this.deptLevel = deptLevel;
		this.deptMngId = deptMngId;
		this.upDeptId = upDeptId;
		this.deptState = deptState;
		this.createrId = createrId;
		this.createTime = createTime;
		this.updaterId = updaterId;
		this.updateTime = updateTime;
	}

	@Id
	@Column(name = "dept_id", unique = true, nullable = false, length = 32)
	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name = "dept_name", nullable = false, length = 32)
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "dept_desc", length = 512)
	public String getDeptDesc() {
		return this.deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}

	@Column(name = "dept_level", nullable = false, length = 2)
	public String getDeptLevel() {
		return this.deptLevel;
	}

	public void setDeptLevel(String deptLevel) {
		this.deptLevel = deptLevel;
	}

	@Column(name = "dept_mng_id", length = 32)
	public String getDeptMngId() {
		return this.deptMngId;
	}

	public void setDeptMngId(String deptMngId) {
		this.deptMngId = deptMngId;
	}

	@Column(name = "up_dept_id", length = 32)
	public String getUpDeptId() {
		return this.upDeptId;
	}

	public void setUpDeptId(String upDeptId) {
		this.upDeptId = upDeptId;
	}

	@Column(name = "dept_state", nullable = false, length = 2)
	public String getDeptState() {
		return this.deptState;
	}

	public void setDeptState(String deptState) {
		this.deptState = deptState;
	}

	@Column(name = "creater_id", nullable = false, length = 32)
	public String getCreaterId() {
		return this.createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	@Column(name = "create_time", nullable = false, length = 14)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "updater_id", length = 32)
	public String getUpdaterId() {
		return this.updaterId;
	}

	public void setUpdaterId(String updaterId) {
		this.updaterId = updaterId;
	}

	@Column(name = "update_time", length = 14)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
