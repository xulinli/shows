package com.redshow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redshow.dto.Users;
import com.redshow.mapper.LoginMapper;
import com.redshow.service.LoginService;
import com.redshow.utis.MD5;
@Service
public class ILoginService implements LoginService {
	
	@Autowired
	private LoginMapper logmap;
	
	@Override
	public Users getUserforLogin(String name,String upass) {
		Users getUser = logmap.getUserParameter(name);
		if(getUser!=null) {
			String encrypt = MD5.encrypt(upass, getUser.getSalt()).substring(0, 20);
			if(encrypt.equals(getUser.getPassword())) {
				Integer state = Integer.valueOf(getUser.getState());
				if (state<2) {
					String username = getUser.getUsername();
					switch (state) {
					case 0:
						logmap.changeState(1,username);
						break;
					case 1:
						logmap.changeState(2,username);
						break;
					default:  
						break;
					}
					return getUser;
				} else {
					Users u=new Users();
					u.setState("-1");
					return u;
				}
			}
		}
		return null;
	}

	@Override
	public Integer getRoleId(Integer userid) {
		int roleName = logmap.roleName(userid);
		if(roleName>0) {
			return roleName;
		}
		return -1;
	}

	@Override
	public Integer logout(String name) {
		Users user = logmap.getUserParameter(name);
		System.out.println(user);
		int result=-1;
		switch (user.getState()) {
		case "1":result=logmap.changeState(0, name);break;
		case "2":result=logmap.changeState(1, name);break;
		default:System.out.println("登出出现异常，异常参数为："+user.getState());break;
		}
		return result;
	}
}
