package com.gxl.biz.impl;

import gxl.mult.code.SecretPst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxl.biz.MemberBiz;
import com.gxl.common.CheckUtils;
import com.gxl.common.Constants;
import com.gxl.dao.PublicDao;
import com.gxl.frd.po.FrdMember;

@Service(value="memberBiz")
public class MemberBizImpl implements MemberBiz{
	@Autowired private PublicDao publicDao;

	@Override
	public void loginChk(FrdMember mbr) throws Exception {
		long start = System.currentTimeMillis();
		boolean bGetUser = false;
		boolean bRightPwd = false;
		String mbrNo = mbr.getMbr().getEmail();
		String mbrPwd = mbr.getMbr().getPassword();
		if (CheckUtils.isNull(mbrNo)) {
			mbr.setErrMsg("login.userNoNull");//用户号不可为空
		}
		if (CheckUtils.isNull(mbrPwd)) {
			mbr.setErrMsg("login.userPassNull");//密码不可为空
		}
		
		String userPass = new String(SecretPst.encrypt(mbrPwd));//密码加密
		//TODO 分别根据账号，手机号，邮箱号从数据库查询用户
		if(!bGetUser){
			mbr.setErrMsg("login.userNoFund");//用户号不存在
		}
		if (!bRightPwd) {
			mbr.setErrMsg("login.userPassErr");//用户密码错误
		}
		mbr.setErrMsg("login.success");
		long end = System.currentTimeMillis();
		System.out.println("登录耗时："+(end-start)+"ms");
		
	}

	@Override
	public void txRgst(FrdMember mbr) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
