package com.gxl.biz;

import java.util.List;

import com.gxl.pm.po.PmFunction;

public interface FunctionBiz {
	public Long countFunctions(PmFunction function) throws Exception;

	public List<PmFunction> getFunctionsPage(PmFunction function,int offset,int rows) throws Exception;
	
	public void txOpFunction(PmFunction function,String operSign,String opUserId) throws Exception;
}
