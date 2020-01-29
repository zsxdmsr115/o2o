package com.jiangbin.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jiangbin.BaseTest;
import com.jiangbin.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest{
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	@Test
	public void testCategoryDao(){
		List<ShopCategory> list = shopCategoryDao.queryShopCategoty(new ShopCategory());
		assertEquals(2, list.size());
		ShopCategory test = new ShopCategory();
		ShopCategory parent = new ShopCategory();
		parent.setShopCategoryId(1L);
		test.setParent(parent);
		List<ShopCategory> list2 = shopCategoryDao.queryShopCategoty(test);
		System.out.println(list2.get(0).getShopCategoryName());
		assertEquals(1, list2.size());
	}
}
