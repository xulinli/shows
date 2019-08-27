package com.redshow.service;

import java.util.List;

import com.redshow.dto.Books;

public interface BookrackService {
	
	/**
	 * 通过用户的id查询该用户已经购买的书籍
	 * @param userid
	 * @return
	 */
	public List<Books> bookracks(Integer userid);
}
