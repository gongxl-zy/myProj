package com.gxl.frd.po;

public class Combo {
	private String value;//实际值
	private String text;//显示值
	private String upValue;//父亲实际值
	
	public Combo() {
		super();
	}
	public Combo(String value, String text, String upValue) {
		super();
		this.value = value;
		this.text = text;
		this.upValue = upValue;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUpValue() {
		return upValue;
	}
	public void setUpValue(String upValue) {
		this.upValue = upValue;
	}
	
}
