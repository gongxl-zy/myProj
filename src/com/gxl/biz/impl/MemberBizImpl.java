package com.gxl.biz.impl;

import gxl.mult.code.DateUtils;
import gxl.mult.code.SecretPst;
import gxl.mult.code.Uuid;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxl.biz.MemberBiz;
import com.gxl.common.CheckUtils;
import com.gxl.common.Constants;
import com.gxl.dao.PublicDao;
import com.gxl.entity.PMbrDetail;
import com.gxl.entity.PMbrInfo;
import com.gxl.entity.PMember;
import com.gxl.frd.po.FrdMember;

@Service(value="memberBiz")
public class MemberBizImpl implements MemberBiz{
	@Autowired private PublicDao publicDao;

	@Override
	public void loginChk(FrdMember mbr) throws Exception {
		long start = System.currentTimeMillis();
		boolean bGetMbr = false;
		String mbrNo = mbr.getMbrNo();
		String mbrPwd = mbr.getMbrPwd();
		if (CheckUtils.isNull(mbrNo)) {
			mbr.setErrMsg("login.userNoNull");//用户号不可为空
			return;
		}
		if (CheckUtils.isNull(mbrPwd)) {
			mbr.setErrMsg("login.userPassNull");//密码不可为空
			return;
		}
		PMember pMbr = null;
		for (int i = 0; i < 3; i++) {
			pMbr = getMbr(i , mbrNo);
			if(pMbr != null){
				bGetMbr = true;
				break;
			}
		}
		//TODO 分别根据账号，手机号，邮箱号从数据库查询用户
		if(!bGetMbr){
			mbr.setErrMsg("login.userNoFund");//用户号不存在
			return;
		}
		String userPass = new String(SecretPst.encrypt(mbrPwd));//密码加密
		if (!userPass.equals(pMbr.getPassword())) {
			mbr.setErrMsg("login.userPassErr");//用户密码错误
		}
		long end = System.currentTimeMillis();
		System.out.println("登录耗时："+(end-start)+"ms");
		
	}
	
	private PMember getMbr(Integer sign,String param) throws Exception{
		String hql = "from PMember where ";
		String strParam = "'" + param + "'";
		if(sign == 0){
			hql = hql + "mbrName="+strParam;
		}else if(sign == 1){
			hql = hql + "phone="+strParam;
		}else if(sign == 2){
			hql = hql + "email="+strParam;
		}
		Iterator<Object> itor = publicDao.selectItorByHql(hql);
		if(itor.hasNext()){
			return (PMember)itor.next();
		}
		return null;
	}

	@Override
	public void txRgst(FrdMember frdMbr) throws Exception {
		String mbrId = Uuid.build();
		PMember mbr = new PMember();
		mbr.setMbrId(mbrId);
		mbr.setPassword(new String(SecretPst.encrypt(frdMbr.getMbrPwd())));
		if(Constants.FRD_RGST_PH.equals(frdMbr.getRgstSign())){
			mbr.setPhone(frdMbr.getMbrNo());
		}else if(Constants.FRD_RGST_EM.equals(frdMbr.getRgstSign())){
			mbr.setEmail(frdMbr.getMbrNo());
		}
		mbr.setRegisterTime(DateUtils.getNowTimeStr());
		mbr.setRecommenderId(Constants.INIT_MBR_ID);
		publicDao.add(mbr);
		
		PMbrInfo mbrInf = new PMbrInfo();
		mbrInf.setMbrId(mbrId);
		mbrInf.setType(Constants.MBR_TRUE);
		mbrInf.setPicUrl(Constants.DFT_PIC_URL);
		mbrInf.setMbrName(frdMbr.getMbrNo());
		mbrInf.setSex(frdMbr.getSex());
		mbrInf.setAge(frdMbr.getAge());
		publicDao.add(mbrInf);
		
		PMbrDetail mbrDt = new PMbrDetail();
		mbrDt.setMbrId(mbrId);
		publicDao.add(mbrDt);
	}

}
