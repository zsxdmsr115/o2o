package com.jiangbin.service;

import java.util.List;


import com.jiangbin.entity.ShopCategory;

public interface ShopCategorySevice {
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);

}
