package com.jiangbin.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiangbin.dao.ShopCategoryDao;
import com.jiangbin.entity.ShopCategory;
import com.jiangbin.service.ShopCategorySevice;
@Service
public class ShopCategoryServiceImpl implements ShopCategorySevice {
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
		return shopCategoryDao.queryShopCategoty(shopCategoryCondition);
	}
}
