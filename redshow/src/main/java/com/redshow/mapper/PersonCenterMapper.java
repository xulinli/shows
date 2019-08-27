package com.redshow.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonCenterMapper {

	/**
	 * 通过用户的 id将上传的照片的名字存在对应的用户的headurl中
	 * @param url
	 * @param id
	 * @return
	 */
	public Integer saveHeadUrl(String url,Integer id);
}
