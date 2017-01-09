package com.gxl.frd.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.gxl.biz.MemberBiz;
import com.gxl.common.BaseFrdAction;
import com.gxl.common.CheckUtils;
import com.gxl.common.Constants;
import com.gxl.frd.po.FrdMember;

@Namespace("/")
public class LoginAction extends BaseFrdAction{
	private static final long serialVersionUID = 2275954075331627014L;
	@Autowired private MemberBiz memberBiz;
	
	FrdMember frdMbr = new FrdMember();
	
	public void setFrdMbr(FrdMember frdMbr) {
		this.frdMbr = frdMbr;
	}
	@Override
	public Object getModel() {
		return frdMbr;
	}
	@Action(value = "home", results = {
			@Result(name = "success", location = "/frd/home_lg.jsp"),
			@Result(name = "login", location = "/frd/home_nlg.jsp") })
	public String home(){
		if(getCurMbr() == null){
			return LOGIN;
		}else{
			return SUCCESS;
		}
	}
	@Action(value = "", results = {
			@Result(name = "success", location = "/frd/home_lg.jsp"),
			@Result(name = "login", location = "/frd/home_nlg.jsp") })
	public String init(){
		if(getCurMbr() == null){
			return LOGIN;
		}else{
			return SUCCESS;
		}
	}
	@Action(value = "login", results = {
			@Result(name = "success", type="redirect", location = "home"),
			@Result(name = "login", location = "/frd/login.jsp") })
	public String login() {//登陆页面
		loginDo();
		if(getCurMbr() == null){
			return LOGIN;
		}else{
			return SUCCESS;
		}
	}
	private void loginDo(){
		if(CheckUtils.isNull(frdMbr.getLoginSign()) || !Constants.FRD_LOGIN.equals(frdMbr.getLoginSign())){
			return;
		}
		try {
			memberBiz.loginChk(frdMbr);
			if(CheckUtils.isNull(frdMbr.getErrMsg())){
				this.getSession().setAttribute(Constants.FRD_LOGIN, frdMbr);
			}
		} catch (Exception e) {
			frdMbr.setErrMsg("login:" + Constants.UNKNOWN_ERROR);
			e.printStackTrace();
		}
	}
	
	@Action(value = "register", results = { 
			@Result(name = "success", type="redirect", location = "home"),
			@Result(name = "error", location = "/frd/register.jsp") })
	public String register() {//注册
		if(1==1)return SUCCESS;
		regusterDo();
		if(getCurMbr() == null){
			return ERROR;
		}else{
			return SUCCESS;
		}
	}
	private void regusterDo(){
		System.out.println("start" + frdMbr.getRgstSign());
		if(CheckUtils.isNull(frdMbr.getRgstSign()) || !(Constants.FRD_RGST_PH.equals(frdMbr.getRgstSign()) || Constants.FRD_RGST_EM.equals(frdMbr.getRgstSign()))){
			return;
		}
		try {
			System.out.println("start1");
			memberBiz.txRgst(frdMbr);
			if(CheckUtils.isNull(frdMbr.getErrMsg())){
				this.getSession().setAttribute(Constants.FRD_LOGIN, frdMbr);
				System.out.println("注册成功");
			}
		} catch (Exception e) {
			frdMbr.setErrMsg("reguster:" + Constants.UNKNOWN_ERROR);
			System.out.println("注册失败");
			e.printStackTrace();
		}
	}
}
