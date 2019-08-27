package com.redshow.dto;

import java.io.Serializable;

public class Users implements Serializable{
	private Integer id;
	private String username;
	private String password;
	private String salt;
	private String sex;
	private String age;
	private String email;
	private String phone;
	private String address;
	private String state;
	private Double integral;
	private String rolename;
	private String haedurl;
	
	

	
	
	
	public String getHaedurl() {
		return haedurl;
	}
	public void setHaedurl(String haedurl) {
		this.haedurl = haedurl;
	}
	public Integer getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", salt=" + salt + ", sex="
				+ sex + ", age=" + age + ", email=" + email + ", phone=" + phone + ", address=" + address + ", state="
				+ state + ", integral=" + integral + ", rolename=" + rolename + "]";
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Double getIntegral() {
		return integral;
	}
	public void setIntegral(Double integral) {
		this.integral = integral;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
}
