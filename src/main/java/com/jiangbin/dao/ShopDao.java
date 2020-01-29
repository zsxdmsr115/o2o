package com.jiangbin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jiangbin.entity.Shop;

public interface ShopDao {
	/**
	 * 分页查询店铺，可输入的条件有：店铺名（模糊），店铺状态，店铺类别，区域id,owner
	 * @param shop
	 * @param rowIndex 从第几开始取数据
	 * @param pageSize 返回的条数
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shop,
			@Param("rowIndex")int rowIndex,@Param("pageSize") int pageSize);
	
	/**
	 * f返回queryShopList总数
	 * @param shopCondition
	 * @return
	 */
	 int queryShopCount(@Param("shopCondition")Shop shopCondition);
	/**
	 * 通过 shop id 查询店铺
	 * @param shopId
	 * @return
	 */
	Shop queryByShopId(long shopId);
	/**
	 * 新增店铺
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);
	/**
	 * 更新店铺
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);
}
