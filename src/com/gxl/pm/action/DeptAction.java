package com.gxl.pm.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.gxl.biz.DeptBiz;
import com.gxl.common.BasePmAction;
import com.gxl.common.Cache;
import com.gxl.pm.po.PmDept;

@Namespace("/pm/dept")
public class DeptAction extends BasePmAction{
	private static final long serialVersionUID = -4835283531740476947L;

	private PmDept dept = new PmDept();
	@Autowired private DeptBiz deptBiz;
	
	public PmDept getDept() {
		return dept;
	}
	public void setDept(PmDept dept) {
		this.dept = dept;
	}
	@Override
	public Object getModel() {
		return dept;
	}
	
	@Action(value = "init", results = {@Result(name = "success", location = "/pm/system/dept.jsp")})
	public String init(){
		return SUCCESS;
	}
	
	@Action(value = "query")
	public void query(){
		try {
			List<PmDept> depts = deptBiz.getDepts();
			this.returnResultJsonTree(depts);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Action(value = "queryList")
	public void queryList(){
		try {
			List<PmDept> depts = Cache.deptList;
			this.returnResultJsonTree(depts);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Action(value = "oper")
	public void oper(){
		try {
			deptBiz.txOpDept(dept,getOperSign(),getCurUser().getUserId());
			returnResultJson("操作成功！",true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
