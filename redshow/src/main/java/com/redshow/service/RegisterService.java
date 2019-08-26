package com.redshow.service;

import java.util.List;

import com.redshow.dto.Local;
import com.redshow.dto.Users;

public interface RegisterService {
	/**
	 * 查询地址
	 * @param inter
	 * @return
	 */
	public List<Local> query(Integer id);
	/**
	 * 根据父级地址的id获取对应的地名
	 * @param inter
	 * @return
	 */
	public String queryLocal(Long id);
	
	/**
	 * 在注册时，通过名字查看数据库中是否存在本用户
	 * @param name
	 * @return
	 */
	public Integer userIsExits(String  name) ;
	
	/**
	 * 注册用户，将用户信息放入数据库
	 * @param user
	 * @return
	 */
	public Integer register(Users user);
 }
