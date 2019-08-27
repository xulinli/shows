package com.redshow.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.redshow.dto.Users;
import com.redshow.service.PersonCenterService;

@Controller
@CrossOrigin
public class CenterOfPerson {
	
	@Autowired
	private PersonCenterService pcs;
	
	@RequestMapping(path="/headupload.do")
	@ResponseBody
	public Integer uploadHead(HttpServletRequest request,MultipartFile upload) {
		//获取将文件保存的路径
		String realPath = request.getSession().getServletContext().getRealPath("/uploads/");
		String path="D:/redshow/headImg";
		 //获取文件名称
	    String filename = upload.getOriginalFilename();
	    System.out.println(filename);
	    //将文件写出，并且删除文件
	    try {
			upload.transferTo(new File(path,filename));
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	    System.out.println(request.getSession().getId());
	    Users user = (Users) request.getSession().getAttribute("user");
	    if(user!=null) {
	    	Integer result = pcs.saveUrl(filename, user.getId());
	    	return result;//若图片正常存入返回数据库受影响的行数
	    }
	    return 0;//用户未登录
	}
	
}
