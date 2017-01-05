package com.gxl.biz;

import com.gxl.frd.po.FrdMember;

public interface MemberBiz {

	/**
	 * 登陆验证(通过则加载会员信息到mbr，否则给errMsg赋值)
	 * @param mbr
	 * @throws Exception
	 */
	public void loginChk(FrdMember mbr) throws Exception;
	
	/**
	 * 注册(通过则加载会员信息到mbr，否则给errMsg赋值)
	 * @param mbr
	 * @throws Exception
	 */
	public void txRgst(FrdMember mbr) throws Exception;
}
