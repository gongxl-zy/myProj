package com.gxl.common;

import gxl.mult.code.BaseAction;

import com.gxl.frd.po.FrdMember;
import com.opensymphony.xwork2.ModelDriven;

public class BaseFrdAction extends BaseAction implements ModelDriven<Object>{
	private static final long serialVersionUID = -967425530422064540L;
	
	public FrdMember getCurMbr(){
		return (FrdMember) this.getSession().getAttribute(Constants.FRD_MBR);
	}
	
	@Override
	public Object getModel() {
		return null;
	}
	
	//设置错误信息并返错误状态
	public String msgErrReturn(String key) {
		String msg = Constants.getDesc(key);
		this.setMsg(msg);
		return ERROR;
	}
}
