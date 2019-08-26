package com.redshow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redshow.dto.Local;
import com.redshow.dto.Users;
import com.redshow.mapper.RegisterMapper;
import com.redshow.service.RegisterService;
import com.redshow.utis.MD5;
@Service
public class IRegisterService implements RegisterService {
	@Autowired
	private  RegisterMapper local;

	@Override
	public List<Local> query(Integer id) {
		return local.queryLocal(id);
	}

	@Override
	public String queryLocal(Long id) {
		return local.query(id);
	}

	@Override
	public Integer userIsExits(String name) {
		Integer isexist = local.getUsersByName(name);
		return isexist;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer register(Users user) {
		String salt = MD5.getRandomStr(10);
		user.setSalt(salt);
		user.setPassword(MD5.encrypt(user.getPassword(), salt).substring(0, 20));
		int issuccess=-1;
		issuccess= local.register(user);
		if(issuccess==1) {
			issuccess=local.getUsersByName(user.getUsername());
			issuccess=local.registerDefaultRole(issuccess, 4);
			return issuccess;
		}
		return issuccess;
	}

	
}
