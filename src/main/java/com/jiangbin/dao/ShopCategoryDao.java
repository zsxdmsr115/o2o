package com.jiangbin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jiangbin.entity.ShopCategory;

public interface ShopCategoryDao {
	List<ShopCategory> queryShopCategoty(@Param("shopCategoryCondition")
	ShopCategory shopCategoryCondition);
}
