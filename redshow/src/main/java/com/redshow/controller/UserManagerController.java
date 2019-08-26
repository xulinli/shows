package com.redshow.controller;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.redshow.dto.Users;
import com.redshow.service.RegisterService;
import com.redshow.service.UserManagerService;


@Controller
@CrossOrigin
public class UserManagerController {

	@Autowired
	private UserManagerService ums;//
	
	@Autowired
	private RegisterService rs;
	/**
	 * 通過給定的條件进行查询
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/userManage.do")
	@ResponseBody
	public PageInfo<Users> userManage(@RequestBody Map<String, Object> map){
		System.out.println(map);
		Integer nowpage = (Integer) map.remove("nowpage");
		Integer perpage = (Integer) map.remove("perpage");
		//设置分页信息(第几页，每页数量)
		PageHelper.startPage(nowpage, perpage);
		//查询所需数据条件
		List<Users> users = ums.getUsers(map);
		System.out.println(users);
        //取记录总条数
        PageInfo<Users> pageInfo = new PageInfo<>(users); 
        System.out.println(pageInfo.getEndRow());
		return pageInfo;
	}
	
	/**
	 * 添加用户，注意这里调用的是注册的方法，没有从新写
	 * @param map
	 * @return
	 */
	@RequestMapping(path="/addUser.do")
	@ResponseBody
	public Integer addUser(@RequestBody Map<String, Object> map) {
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
			user.setAddress((String) map.get("address"));
			user.setState("0");
			Integer register = rs.register(user);
			return 1; 
		}else {  
			return -1;
		}
	}
	
	
	/**
	 * 删除用户
	 * @param username
	 */
	@RequestMapping(value="/deleteUser.do")
	public void deleteUser(String username) {
		ums.deleteUser(username);
	}
	
	
	@RequestMapping(path="getUserforEdit.do")
	@ResponseBody
	public Users getUserforEdit(String username) {
		Users user = ums.getUser(username);
		if(user!=null) {
			return user;
		}
		return null;
	}
	/**
	 * 修改用户
	 * @param map
	 * @return
	 */
	@RequestMapping(path="editUser.do")
	@ResponseBody
	public Integer editUser(@RequestBody Map<String, Object> map) {
		System.out.println(map);
		if(map!=null) {
			Users user = new Users();
			user.setUsername((String) map.get("username"));
			user.setSex((String) map.get("sex"));
			user.setAge((String) map.get("age"));
			user.setPhone((String) map.get("phone"));
			user.setEmail((String) map.get("email"));
			user.setAddress((String) map.get("address"));
			user.setRolename((String) map.get("role"));
			Integer result = ums.editUser(user);
			return result;
		}
		
		return -1;
	}
	
}
