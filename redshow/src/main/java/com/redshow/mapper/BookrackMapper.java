package com.redshow.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.redshow.dto.Books;
@Mapper
public interface BookrackMapper {

	/**
	 * 查询所有已经付款的书籍
	 * @param id
	 * @return
	 */
	public List<Books> getOrderList(Integer id);
	
	/**
	 * 查询已经加入购物车，但是还没有付款的书籍
	 * @param id
	 * @return
	 */
	public List<Books> getNotBuyList(Integer id);
}
