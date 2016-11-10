package com.gxl.common;

import gxl.mult.code.BaseAction;

import com.gxl.entity.PUser;

public class BasePmAction extends BaseAction{
	private static final long serialVersionUID = -967425530422064540L;
	
	private String operSign;
	
	public String getOperSign() {
		return operSign;
	}
	public void setOperSign(String operSign) {
		this.operSign = operSign;
	}
	
	public PUser getCurUser(){
		return (PUser) this.getSession().getAttribute(Constants.PM_USER);
	}

	//设置错误信息并返错误状态
	public String msgErrReturn(String key) {
		String msg = Constants.getDesc(key);
		this.setMsg(msg);
		return ERROR;
	}
}
