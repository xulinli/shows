package com.redshow.dto;

public class Local {
	private Integer  codeid;
	private Integer  parentid;
	private String   cityName;
	public Integer getCodeid() {
		return codeid;
	}
	public void setCodeid(Integer codeid) {
		this.codeid = codeid;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	@Override
	public String toString() {
		return "Local [codeid=" + codeid + ", parentid=" + parentid + ", cityName=" + cityName + "]";
	}
	
}
