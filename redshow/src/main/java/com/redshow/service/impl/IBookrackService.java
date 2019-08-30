package com.redshow.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redshow.dto.Books;
import com.redshow.mapper.BookrackMapper;
import com.redshow.service.BookrackService;

@Service
public class IBookrackService implements BookrackService {
	
	@Autowired
	private BookrackMapper bm;
	
	@Override
	public List<Books> bookracks(Integer userid) {
		List<Books> list=new ArrayList<>();
		List<Books> orders = bm.getOrderList(userid);
		if(orders!=null && orders.size()>0) {
			for (Books book : orders) {
				book.setState(0);//添加状态0，表示这个物品时已经付款后的
			}
			list.addAll(orders);
		}
		List<Books> notBuyList = bm.getNotBuyList(userid);
		if(notBuyList!=null &&notBuyList.size()>0) {
			for (Books book : orders) {
				book.setState(1);//添加状态1，表示这个物品加入书架但是没有付款的
			}
			list.addAll(orders);
		}
		return list;
	}

}
