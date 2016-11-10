package com.gxl.pm.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.gxl.biz.UserBiz;
import com.gxl.common.BasePmAction;
import com.gxl.common.Cache;
import com.gxl.pm.po.PmUser;

@Namespace("/pm/user")
public class UserAction extends BasePmAction{
	private static final long serialVersionUID = -4835283531740476947L;

	private PmUser user = new PmUser();
	@Autowired private UserBiz userBiz;
	
	public PmUser getUser() {
		return user;
	}
	public void setUser(PmUser user) {
		this.user = user;
	}

	@Override
	public Object getModel() {
		return user;
	}
	
	@Action(value = "init", results = {@Result(name = "success", location = "/pm/system/user.jsp")})
	public String init(){
		return SUCCESS;
	}
	
	@Action(value = "query")
	public void query(){
		try {
			Long sum = userBiz.countUsers(user);
			List<PmUser> users = userBiz.getUsersPage(user, getOffset(), getRows());
			this.returnEasyUIResultJson(users,sum);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Action(value = "queryList")
	public void queryList(){
		try {
			List<PmUser> users = Cache.getUserList(user.getUserNo());
			this.returnEasyUIResultJson(users,new Long(users.size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Action(value = "oper")
	public void oper(){
		try {
			userBiz.txOpUser(user,getOperSign(),getCurUser().getUserId());
			returnResultJson("操作成功！",true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
