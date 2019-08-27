package com.redshow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redshow.mapper.PersonCenterMapper;
import com.redshow.service.PersonCenterService;

@Service
public class IPersonCenterService implements PersonCenterService{
	
	@Autowired
	private PersonCenterMapper pcm;
	
	@Override
	public Integer saveUrl(String url, Integer id) {
		Integer res= pcm.saveHeadUrl(url, id);
		if(res>0) {
			return res;
		}
		return -1;
	}



}
