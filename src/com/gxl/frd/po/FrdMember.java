package com.gxl.frd.po;


public class FrdMember {
	private String mbrId;//会员ID
	private String mbrName;//会员名
	private String mbrNo;//会员登录账号
	private String mbrPwd;//会员密码
	private String age;//年龄
	private String sex;//性别
	
	private String loginSign;//值为“frdLogin”表示登陆操作
	private String rgstSign;//分手机号（phone）注册和邮箱（email）注册2种
	private String errMsg;//报错信息
	
	public String getMbrId() {
		return mbrId;
	}
	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}
	public String getMbrNo() {
		return mbrNo;
	}
	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
	}
	public String getMbrPwd() {
		return mbrPwd;
	}
	public void setMbrPwd(String mbrPwd) {
		this.mbrPwd = mbrPwd;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLoginSign() {
		return loginSign;
	}
	public void setLoginSign(String loginSign) {
		this.loginSign = loginSign;
	}
	public String getRgstSign() {
		return rgstSign;
	}
	public void setRgstSign(String rgstSign) {
		this.rgstSign = rgstSign;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getMbrName() {
		return mbrName;
	}
	public void setMbrName(String mbrName) {
		this.mbrName = mbrName;
	}
}
