package com.redshow.service;

import java.util.List;
import java.util.Map;

import com.redshow.dto.Users;

public interface UserManagerService {
	/**
	 * 查询所有的用户及其对应的角色名，同时将数字的角色id变更为汉字
	 * @param map
	 * @return
	 */
	public List<Users> getUsers(Map<String, Object> map);
	
	
	/**
	 * 通过用户名讲数据库的对应的用户信息和对应的角色映射删除
	 * @param name
	 * @return
	 */
	public Integer deleteUser(String name);

	
	/**
	 * 通过名字获取该用户的所有信息，包括角色信息
	 * @param name
	 * @return
	 */
	public Users getUser(String name);
	
	
	/**
	 * 通过controller穿过来的Users对象对数据库的user表和角色映射表进行修改role_user
	 * @param user
	 * @return
	 */
	public Integer editUser(Users user);
}
