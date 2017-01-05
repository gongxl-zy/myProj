package com.gxl.frd.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.gxl.biz.MemberBiz;
import com.gxl.common.BasePmAction;
import com.gxl.common.CheckUtils;
import com.gxl.common.Constants;
import com.gxl.frd.po.FrdMember;

@Namespace("/")
public class LoginAction extends BasePmAction{
	private static final long serialVersionUID = 2275954075331627014L;
	@Autowired private MemberBiz memberBiz;
	
	FrdMember mbr = new FrdMember();
	
	public void setMbr(FrdMember mbr) {
		this.mbr = mbr;
	}

	@Override
	public Object getModel() {
		return mbr;//模型驱动
	}
	
	@Action(value = "", results = {
			@Result(name = "success", location = "/frd/home_lg.jsp"),
			@Result(name = "login", location = "/frd/home_nlg.jsp") })
	public String init(){
		Object obj = getSession().getAttribute(Constants.FRD_MBR);
		if(obj == null){
			return LOGIN;
		}else{
			return SUCCESS;
		}
	}
	@Action(value = "login", results = { 
			@Result(name = "success", location = "/frd/home_lg.jsp"),
			@Result(name = "login", location = "/frd/login.jsp") })
	public String login() {//登陆页面
		loginDo();
		Object obj = getSession().getAttribute(Constants.FRD_MBR);
		if(obj == null){
			return LOGIN;
		}else{
			return SUCCESS;
		}
	}
	private void loginDo(){
		if(CheckUtils.isNull(mbr.getLoginSign()) || !Constants.FRD_LOGIN.equals(mbr.getLoginSign())){
			return;
		}
		try {
			memberBiz.loginChk(mbr);
			if(CheckUtils.isNull(mbr.getErrMsg())){
				this.getSession().setAttribute(Constants.FRD_LOGIN, mbr);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
