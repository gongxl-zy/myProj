package com.gxl.biz;

import java.util.List;

import com.gxl.pm.po.PmDept;

public interface DeptBiz {
	public List<PmDept> getDepts() throws Exception;
	
	public void txOpDept(PmDept dept,String operSign,String opUserId) throws Exception;
}
