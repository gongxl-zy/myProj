package com.gxl.pm.po;

import com.gxl.common.Cache;
import com.gxl.common.ReflectUtils;
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
	public PmFunction(PFunction faObj){
		ReflectUtils.setChildPo(this,faObj);
	}

}
