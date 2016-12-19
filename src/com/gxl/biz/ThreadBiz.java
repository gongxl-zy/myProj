package com.gxl.biz;

import java.util.List;

public interface ThreadBiz {
	/**
	 * 添加地区代码表，更新链接表
	 * @throws Exception
	 */
	public void txAddAreaCode(List<String> areaList) throws Exception;
}
