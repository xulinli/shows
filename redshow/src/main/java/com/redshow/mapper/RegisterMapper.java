package com.redshow.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.redshow.dto.Local;
import com.redshow.dto.Users;

@Mapper
public interface RegisterMapper {
	/**
	 * 查询地址
	 * @param inter
	 * @return
	 */
	public List<Local> queryLocal(Integer id);
	
	/**
	 * 通过id查询对应的地名
	 * @param inter
	 * @return
	 */
	public String query(Long id);
	
	/**
	 * 通过名字查询该用户的id信息，表示存在该用户
	 * @param name
	 * @return
	 */
	public Integer getUsersByName(String name);
	
	/**
	 * 保存用户信息 
	 * @param user
	 */
	public Integer register(@Param("user")Users user);
	
	/**
	 * 给注册的用户一个默认的角色
	 * @return
	 */
	public Integer registerDefaultRole(@Param("userid")Integer name,@Param("roleId")Integer id);}
