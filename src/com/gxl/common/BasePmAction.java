package com.gxl.common;

import gxl.mult.code.BaseAction;

import com.gxl.entity.PUser;
import com.opensymphony.xwork2.ModelDriven;

public class BasePmAction extends BaseAction implements ModelDriven<Object>{
	private static final long serialVersionUID = -967425530422064540L;
	
	private String operSign;
	
	public String getOperSign() {
		return operSign;
	}
	public void setOperSign(String operSign) {
		this.operSign = operSign;
	}

	@Override
	public Object getModel() {
		return null;
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
