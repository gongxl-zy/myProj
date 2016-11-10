package com.gxl.pm.po;

import com.gxl.common.Cache;
import com.gxl.entity.PFunction;

public class PmFunction extends PFunction {
	private static final long serialVersionUID = 5266410848290272395L;
	
	public String getCreaterName() {// 创建人姓名
		return Cache.getName(getCreaterId());
	}

	public String getUpdaterName() {// 更新人姓名
		return Cache.getName(getUpdaterId());
	}
	public PmFunction(){
		super();
	}
	public PmFunction(PFunction func){
		super();
		setFuncId(func.getFuncId());
		setFuncCode(func.getFuncCode());
		setFuncName(func.getFuncName());
		setFuncIcon(func.getFuncIcon());
		setFuncSort(func.getFuncSort());
		setFuncState(func.getFuncState());
		setFuncDesc(func.getFuncDesc());
		setCreaterId(func.getCreaterId());
		setCreateTime(func.getCreateTime());
		setUpdaterId(func.getUpdaterId());
		setUpdateTime(func.getUpdateTime());
	}

}
