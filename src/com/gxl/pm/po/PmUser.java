package com.gxl.pm.po;

import com.gxl.common.Cache;
import com.gxl.entity.PUser;

public class PmUser extends PUser{
	private static final long serialVersionUID = -7229550566609016391L;

	public String getRoleName(){// 角色
		return Cache.getName(getRoleId());
	}
	public String getDeptName(){// 部门
		return Cache.getName(getDeptId());
	}
	public String getCreaterName() {// 创建人姓名
		return Cache.getName(getCreaterId());
	}
	public String getUpdaterName() {// 更新人姓名
		return Cache.getName(getUpdaterId());
	}
	private String oldPwd;//旧密码
	private String newPwd;//新密码
	
	public PmUser(){
		super();
	}
	public PmUser(PUser user){
		super();
		setUserId(user.getUserId());
		setUserNo(user.getUserNo());
		setUserPwd(user.getUserPwd());
		setUserName(user.getUserName());
		setRoleId(user.getRoleId());
		setDeptId(user.getDeptId());
		setUserLevel(user.getUserLevel());
		setUserPhone(user.getUserPhone());
		setUserEmail(user.getUserEmail());
		setUserState(user.getUserState());
		setIsOnline(user.getIsOnline());
		setOnlineIp(user.getOnlineIp());
		setCreaterId(user.getCreaterId());
		setCreateTime(user.getCreateTime());
		setUpdaterId(user.getUpdaterId());
		setUpdateTime(user.getUpdateTime());
	}
	
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
}
