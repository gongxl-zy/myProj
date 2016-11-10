package com.gxl.pm.po;

import com.gxl.common.Cache;
import com.gxl.entity.PRole;

public class PmRole extends PRole {
	private static final long serialVersionUID = 2287477283077635590L;

	public String getCreaterName() {// 创建人姓名
		return Cache.getName(getCreaterId());
	}

	public String getUpdaterName() {// 更新人姓名
		return Cache.getName(getUpdaterId());
	}
	public PmRole(){
		super();
	}
	public PmRole(PRole role){
		super();
		setRoleId(role.getRoleId());
		setRoleName(role.getRoleName());
		setRoleDesc(role.getRoleDesc());
		setRoleState(role.getRoleState());
		setCreaterId(role.getCreaterId());
		setCreateTime(role.getCreateTime());
		setUpdaterId(role.getUpdaterId());
		setUpdateTime(role.getUpdateTime());
	}

	@Override
	public String toString() {
		return "PmRole [getRoleId()=" + getRoleId() + ", getRoleName()="
				+ getRoleName() + ", getRoleDesc()=" + getRoleDesc()
				+ ", getRoleState()=" + getRoleState() + ", getCreaterId()="
				+ getCreaterId() + ", getCreateTime()=" + getCreateTime()
				+ ", getUpdaterId()=" + getUpdaterId() + ", getUpdateTime()="
				+ getUpdateTime() + "]";
	}

}
