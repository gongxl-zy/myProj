package com.gxl.pm.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.gxl.biz.RoleBiz;
import com.gxl.common.BasePmAction;
import com.gxl.common.Cache;
import com.gxl.pm.po.PmRole;

@Namespace("/pm/role")
public class RoleAction extends BasePmAction{
	private static final long serialVersionUID = -4835283531740476947L;

	private PmRole role = new PmRole();
	@Autowired private RoleBiz roleBiz;
	
	public PmRole getRole() {
		return role;
	}
	public void setRole(PmRole role) {
		this.role = role;
	}

	@Override
	public Object getModel() {
		return role;
	}
	
	@Action(value = "", results = {@Result(name = "success", location = "/pm/system/role.jsp")})
	public String init(){
		return SUCCESS;
	}
	
	@Action(value = "query")
	public void query(){
		try {
			Long sum = roleBiz.countRoles(role);
			List<PmRole> roles = roleBiz.getRolesPage(role, getOffset(), getRows());
			this.returnEasyUIResultJson(roles,sum);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Action(value = "queryList")
	public void queryList(){
		try {
			List<PmRole> roles = Cache.roleList;
			this.returnResultJsonTree(roles);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Action(value = "oper")
	public void oper(){
		try {
			roleBiz.txOpRole(role,getOperSign(),getCurUser().getUserId());
			returnResultJson("操作成功！",true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
