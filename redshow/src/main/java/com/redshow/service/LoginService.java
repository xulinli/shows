package com.redshow.service;

import com.redshow.dto.Users;

public interface LoginService {
	/**
	 * 通过名字查询该用户的所有资料
	 * @param name
	 * @return
	 */
	public Users getUserforLogin(String name,String upass);
	
	/**
	 * 同过userid查询对应的角色id
	 * @param userid
	 * @return
	 */
	public Integer getRoleId(Integer userid);
	
	/**
	 * 通过名字更改用户的状态
	 * @param name
	 * @return
	 */
	public Integer logout(String name);
}
