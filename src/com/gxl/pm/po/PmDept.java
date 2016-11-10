package com.gxl.pm.po;

import com.gxl.common.Cache;
import com.gxl.entity.PDept;

public class PmDept extends PDept{
	private static final long serialVersionUID = 6482497776873318761L;
	
	public String getDeptMngName() {//部门负责人姓名
		return Cache.getName(getDeptMngId());
	}
	public String getUpDeptName() {//父菜单名称
		return Cache.getName(getUpDeptId());
	}
	public String getCreaterName() {//创建人姓名
		return Cache.getName(getCreaterId());
	}
	public String getUpdaterName() {//更新人姓名
		return Cache.getName(getUpdaterId());
	}
	
	public PmDept(){
		super();
	}
	public PmDept(PDept dept) {
		setDeptId(dept.getDeptId());
		setDeptName(dept.getDeptName());
		setDeptDesc(dept.getDeptDesc());
		setDeptLevel(dept.getDeptLevel());
		setDeptMngId(dept.getDeptMngId());
		setUpDeptId(dept.getUpDeptId());
		setDeptState(dept.getDeptState());
		setCreaterId(dept.getCreaterId());
		setCreateTime(dept.getCreateTime());
		setUpdaterId(dept.getUpdaterId());
		setUpdateTime(dept.getUpdateTime());
	}
}
