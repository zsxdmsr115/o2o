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
import com.jiangbin.dto.ImageHolder;
import com.jiangbin.dto.ShopExecution;
import com.jiangbin.entity.Area;
import com.jiangbin.entity.PersonInfo;
import com.jiangbin.entity.Shop;
import com.jiangbin.entity.ShopCategory;
import com.jiangbin.enume.ShopStateEnum;
import com.jiangbin.exception.ShopOperationException;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class ShopServiceTest extends BaseTest{

	@Autowired
	private ShopService shopService;
	@Test
	public void testgetShopList(){
		Shop shop = new Shop();
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(1L);
		ShopExecution shopExecution = shopService.getShopList(shop, 1, 2);
		System.out.println("店铺列表书"+shopExecution.getShopList().size());
		System.out.println("店铺列表书"+shopExecution.getCount());

	}
	@Test
	@Ignore
	public void testModifyShop() throws ShopOperationException,FileNotFoundException{
		Shop shop =new Shop();
		shop.setShopId(1L);
		shop.setShopName("修改后的店铺名称");
		File shopImg = new File("D:/projectdev/image/upload/item/shop/21/2020012823232117413.jpg");
		InputStream is = new FileInputStream(shopImg);
		ImageHolder thumail = new ImageHolder( "2020012823232117413.jpg",is);
		ShopExecution shopExecution = shopService.modifyShop(shop, thumail);
		System.out.println("新的图片"+shopExecution.getShop().getShopImg());
	}
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
		shop.setShopName("测试店铺3");
		shop.setShopDesc("test3");
		shop.setShopAddr("test3");
		shop.setPhone("test3");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg = new File("C:/Users/zsxdmsr115/Desktop/2017061320371786788.jpg");
		InputStream in = new FileInputStream(shopImg);
		ImageHolder thumail = new ImageHolder( shopImg.getName(),in);
		ShopExecution se = shopService.addShop(shop,thumail);
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	
	}
}
