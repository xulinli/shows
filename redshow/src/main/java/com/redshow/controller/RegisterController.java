package com.redshow.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redshow.dto.Local;
import com.redshow.dto.Users;
import com.redshow.service.RegisterService;

@Controller
@CrossOrigin
public class RegisterController  {
	@Autowired
	private RegisterService qli;
	
	
	/**
	 * 省市县3级联动，输入地址
	 * @param parentid
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(path="/serchLocal.do",method=RequestMethod.POST)
	@ResponseBody
	public List<Local> local(Integer parentid) {
		List<Local> query = qli.query(parentid);
		return query;
	}
	
	/**
	 * 通过姓名查看用户是否已经被注册
	 * @param name
	 * @return
	 */
	@PostMapping(value="/yanzhengName.do")
	@ResponseBody
	public int hasName(@RequestParam(name="user") String name) {
		Integer isexist = qli.userIsExits(name);
		if(isexist!=null&&isexist>0) {
			return -1;
		}else {
			return 1;
		}
	}
	/**
	 * 注册用户
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/register.do")
	@ResponseBody
	public Integer register(@RequestBody Map<String, Object> map) {
		Users user;
		String pass1 = (String) map.get("password");
		String pass2 = (String) map.get("newpwd");
		if (pass1.equals(pass2)) {
			user = new Users();
			user.setUsername((String) map.get("username"));
			user.setPassword((String) map.get("password"));
			user.setSex((String) map.get("sex"));
			user.setAge((String) map.get("age"));
			user.setPhone((String) map.get("phone"));
			user.setEmail((String) map.get("email"));
			String addressId1=qli.queryLocal(Long.valueOf((String) map.get("addres1")));
			String addressId2=qli.queryLocal(Long.valueOf((String) map.get("addres2")));
			String addressId3=qli.queryLocal(Long.valueOf((String) map.get("addres2")));
			user.setAddress(addressId1+addressId2+addressId3); 
			user.setState("0");
			Integer register = qli.register(user);
			return 1; 
		}else {  
			return -1;
		}
		
	}
	
	
	
}
