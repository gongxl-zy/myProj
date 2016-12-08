package com.gxl.pm.po;

import com.gxl.common.Cache;
import com.gxl.common.ReflectUtils;
import com.gxl.entity.PRole;

public class PmRole extends PRole {
	private static final long serialVersionUID = 2287477283077635590L;

	public String getCreaterName() {// 创建人姓名
		return Cache.getName(getCreaterId());
	}

	public String getUpdaterName() {// 更新人姓名
		return Cache.getName(getUpdaterId());
	}
	public PmRole(){
		super();
	}
	public PmRole(PRole faObj){
		ReflectUtils.setChildPo(this,faObj);
	}

}
