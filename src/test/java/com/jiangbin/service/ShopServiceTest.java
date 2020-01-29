package com.jiangbin.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jiangbin.BaseTest;
import com.jiangbin.dto.ShopExecution;
import com.jiangbin.entity.Area;
import com.jiangbin.entity.PersonInfo;
import com.jiangbin.entity.Shop;
import com.jiangbin.entity.ShopCategory;
import com.jiangbin.enume.ShopStateEnum;

public class ShopServiceTest extends BaseTest{

	@Autowired
	private ShopService shopService;
	@Test
	public void testAddShop() throws FileNotFoundException{

		Shop shop = new Shop();
		
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("≤‚ ‘µÍ∆Ã3");
		shop.setShopDesc("test3");
		shop.setShopAddr("test3");
		shop.setPhone("test3");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("…Û∫À÷–");
		File shopImg = new File("C:/Users/zsxdmsr115/Desktop/2017061320371786788.jpg");
		InputStream in = new FileInputStream(shopImg);
		ShopExecution se = shopService.addShop(shop,in ,shopImg.getName());
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	
	}
}
