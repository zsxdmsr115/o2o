package com.jiangbin.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jiangbin.BaseTest;
import com.jiangbin.entity.Area;

public class AreaDaoTest extends BaseTest{
	@Autowired
	private AreaDao areaDao;
	@Test
	public void testQueryArea(){
		List<Area> list = areaDao.queryArea();
		assertEquals(2, list.size());
	}
}
