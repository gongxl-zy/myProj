package com.gxl.pm.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.gxl.biz.FunctionBiz;
import com.gxl.common.BasePmAction;
import com.gxl.pm.po.PmFunction;

@Namespace("/pm/function")
public class FunctionAction extends BasePmAction{
	private static final long serialVersionUID = -4835283531740476947L;

	private PmFunction function = new PmFunction();
	@Autowired private FunctionBiz functionBiz;
	
	public PmFunction getFunction() {
		return function;
	}
	public void setFunction(PmFunction function) {
		this.function = function;
	}

	@Override
	public Object getModel() {
		return function;
	}
	
	@Action(value = "init", results = {@Result(name = "success", location = "/pm/system/function.jsp")})
	public String init(){
		return SUCCESS;
	}
	
	@Action(value = "query")
	public void query(){
		try {
			Long sum = functionBiz.countFunctions(function);
			List<PmFunction> functions = functionBiz.getFunctionsPage(function, getOffset(), getRows());
			this.returnEasyUIResultJson(functions,sum);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Action(value = "oper")
	public void oper(){
		try {
			functionBiz.txOpFunction(function,getOperSign(),getCurUser().getUserId());
			returnResultJson("操作成功！",true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
