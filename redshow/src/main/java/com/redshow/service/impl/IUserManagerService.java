package com.redshow.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redshow.dto.Users;
import com.redshow.mapper.UserManagerMapper;
import com.redshow.service.UserManagerService;
@Service
public class IUserManagerService implements UserManagerService {

	@Autowired
	private UserManagerMapper umap;
	
	//查询部分
	@Override
	public List<Users> getUsers(Map<String, Object> map) {
		List<Users> queryUsers = umap.queryUsers(map);
		if(queryUsers!=null) {
			return queryUsers;
		}
		return null;
	}
	//删除部分
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer deleteUser(String name) {
		Integer result=-1;
		result = umap.getUsersId(name);
		if(result>0) {
			umap.deleteRole_User(result);
			umap.deleteUers(result);
		}
		return result;
	}
	
	//修改部分
	@Override
	public Users getUser(String name) {
		Users userInfo = umap.getUserInfo(name);
		return userInfo;
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer editUser(Users user) {
		Integer  result=-1;
		result = umap.getUsersId(user.getUsername());
		if(result>0) {
			result = umap.editUser(user, result);
			result = umap.editUser_Role(Integer.valueOf(user.getRolename()), result);
		}
		return result;
	}


}
