package com.redshow.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.redshow.dto.Users;
@Mapper
public interface UserManagerMapper {
	//查询的呢哦荣
	/**
	 * 查询所有的用户及其对应的角色名
	 * @param map
	 * @return
	 */
	public List<Users> queryUsers(@Param("map")Map<String,Object> map);

	//删除的内容
	/**
	 * 同过名字 查询到该用户的数据库的id
	 * @param name
	 * @return
	 */
	public Integer getUsersId(String name);
	/**
	 * 通过userid删除对应的用户信息 
	 * @param userid
	 * @return
	 */
	public Integer deleteUers(Integer userid);
	/**
	 * 
	 * 同过userid删除对应的Role_User表中的数据
	 * @param userid
	 * @return
	 */
	public Integer deleteRole_User(Integer userid);
	
	//修改内容
	/**
	 * 通过用户的名字获取对应的用户信息包括角色信息 
	 * @param name
	 * @return
	 */
	public Users getUserInfo(String name);
	/**
	 * 通过id对user表相应的字段尽心修改
	 * @param user
	 * @return
	 */
	public Integer editUser(@Param("user")Users user,@Param("id")Integer id);
	
	/**
	 * 通用户在user表的id对user_role表进行修改
	 * @param user
	 * @param id
	 * @return
	 */
	public Integer editUser_Role(@Param("roleid")Integer roleid,@Param("userid")Integer userid);
}
