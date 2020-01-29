package com.jiangbin.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jiangbin.BaseTest;
import com.jiangbin.entity.Area;

public class AreaServiceTest extends BaseTest{
	@Autowired
	private AreaService areaService;
	@Test
	public void testAreaList(){
		List<Area> areaList = areaService.getAreaList();
		assertEquals("Î÷Ô·", areaList.get(0).getAreaName());
		
	}
}
