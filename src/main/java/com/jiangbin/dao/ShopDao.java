package com.jiangbin.dao;

import com.jiangbin.entity.Shop;

public interface ShopDao {
	/**
	 * ��������
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);
	/**
	 * ���µ���
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);
}
