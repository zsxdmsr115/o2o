package com.jiangbin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiangbin.dao.AreaDao;
import com.jiangbin.entity.Area;
import com.jiangbin.service.AreaService;
@Service
public class AreaServiceImpl  implements AreaService{
	@Autowired
	private AreaDao areaDao;

	public List<Area> getAreaList() {
		return areaDao.queryArea();
	}
	
	
}
