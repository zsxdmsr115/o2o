package com.jiangbin.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jiangbin.BaseTest;
import com.jiangbin.entity.Area;
import com.jiangbin.entity.PersonInfo;
import com.jiangbin.entity.Shop;
import com.jiangbin.entity.ShopCategory;

public class ShopDaoTest extends BaseTest{
	@Autowired
	private ShopDao shopDao;
	@Test
	public void testInsertShop(){
		Shop shop = new Shop();
		shop.setShopId(1L);
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("≤‚ ‘µÍ∆Ã");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("test");
		shop.setShopImg("TEST");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("…Û∫À÷–");
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1, effectedNum);
		
	}
	@Test
	public void testUpdateShop(){
		Shop shop = new Shop();
		shop.setShopId(1L);
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		
		
		shop.setShopDesc("≤‚ ‘√Ë ˆ");
		shop.setShopAddr("≤‚ ‘µÿ÷∑");
		shop.setPhone("test");
		shop.setShopImg("TEST");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("…Û∫À÷–");
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
		
	}
}
