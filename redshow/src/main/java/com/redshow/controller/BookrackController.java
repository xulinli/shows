package com.redshow.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redshow.dto.Books;
import com.redshow.dto.Users;
import com.redshow.service.BookrackService;

@Controller
@CrossOrigin
public class BookrackController {

	@Autowired
	private BookrackService bc;
	
	@RequestMapping(path="/loadbookrack.do")
	@ResponseBody
	public Object personBookrack(HttpServletRequest req) {
		Users user = (Users) req.getSession().getAttribute("user");
		if(user!=null) {
			List<Books> bookracks = bc.bookracks(user.getId());
			if(bookracks!=null&& bookracks.size()>0) return bookracks;
		}
		return -1;
	}
}
