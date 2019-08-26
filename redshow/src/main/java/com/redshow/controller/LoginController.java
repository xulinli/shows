package com.redshow.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redshow.dto.Users;
import com.redshow.service.LoginService;

@Controller
@CrossOrigin
public class LoginController {
	
	@Autowired
	private LoginService ls;
	
	@RequestMapping(value="/login.do")
	@ResponseBody
	public Map<String, Object> login(String username,String password,HttpServletRequest req) {
		System.out.println(req.getSession().getId());
		Map<String, Object> map=new HashMap<String, Object>();
		if (!"".equals(username)) {    
			if (!"".equals(password)) {
				Users loginuser = ls.getUserforLogin(username, password);
				System.out.println(loginuser); 
				if (loginuser != null) {
					Integer state = Integer.valueOf(loginuser.getState());
					if(state==-1) {
						map.put("rersult", 2);
					}else {
						System.out.println(loginuser.getId());
						Integer roleId = ls.getRoleId(loginuser.getId());
						String role;
						switch (roleId) { 
							case 1:role="系统管理员";break;
							case 2:role="普通管理员";break;
							case 3:role="商户";break;
							case 4:role="注册用户";break;
							default:role="游客";break;
						}
						loginuser.setRolename(role);
						HttpSession session = req.getSession();
						session.setAttribute("user",loginuser);
						session.setMaxInactiveInterval(15*60);
						map.put("user", loginuser); 
						map.put("rersult", 1);
					}
				}  else {
					map.put("rersult", -1);
				}
			} else {
				map.put("rersult", -3);
			}
		} else {
			map.put("rersult", -2);
		}  
		return map;
	}
	
	 
	@RequestMapping(value="/islogin.do") 
	@ResponseBody
	public Object isLogin(HttpServletRequest req) {
		System.out.println(req.getSession().getId());
		Object user = req.getSession().getAttribute("user");
		if(user!=null && user instanceof Users) {
			return user;
		}
		return -1;
	}
	  
	@RequestMapping(value="/logout.do")
	@ResponseBody
	public Integer logout(HttpServletRequest req,String name) {
		System.out.println(req.getSession().getId());
		Object user = req.getSession().getAttribute("user");
		if(user != null) {
			Integer logout = ls.logout(name);
			req.getSession().removeAttribute("user");
			return logout;
		} 
		return -1; 
	}
}
