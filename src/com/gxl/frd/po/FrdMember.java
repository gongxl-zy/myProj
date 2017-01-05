package com.gxl.frd.po;

import com.gxl.entity.PMbrDetail;
import com.gxl.entity.PMbrInfo;
import com.gxl.entity.PMember;

public class FrdMember {
	private PMember mbr = new PMember();
	private PMbrInfo mbrInf = new PMbrInfo();
	private PMbrDetail mbrDt = new PMbrDetail();
	private String loginSign;//值为“frdLogin”表示登陆操作
	private String errMsg;//报错信息
	
	public PMember getMbr() {
		return mbr;
	}
	public void setMbr(PMember mbr) {
		this.mbr = mbr;
	}
	public PMbrInfo getMbrInf() {
		return mbrInf;
	}
	public void setMbrInf(PMbrInfo mbrInf) {
		this.mbrInf = mbrInf;
	}
	public PMbrDetail getMbrDt() {
		return mbrDt;
	}
	public void setMbrDt(PMbrDetail mbrDt) {
		this.mbrDt = mbrDt;
	}
	public String getLoginSign() {
		return loginSign;
	}
	public void setLoginSign(String loginSign) {
		this.loginSign = loginSign;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
}
