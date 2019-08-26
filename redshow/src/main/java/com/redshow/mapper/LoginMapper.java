package com.redshow.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.redshow.dto.Users;
@Mapper
public interface LoginMapper {
	/**
	 * 通过用户名获取到该用户的所有资料
	 * @param uname
	 * @return
	 */
	public Users getUserParameter(String uname);
	
	/**
	 * 通过名字查询到角色
	 * @param name
	 * @return
	 */
	public int roleName(Integer userid);
	
	/**
	 * 登出
	 * @param name
	 * @return
	 */
	public int changeState(@Param("state")Integer state, @Param("name")String name );
}
