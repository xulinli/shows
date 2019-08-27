package com.redshow.service;

public interface PersonCenterService {

	/**
	 * 上传头像
	 * @param url 头像的名字
	 * @param id 用户的id
	 * @return
	 */
	public Integer saveUrl(String url,Integer id);
}
