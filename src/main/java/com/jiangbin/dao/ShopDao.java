package com.jiangbin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jiangbin.entity.Shop;

public interface ShopDao {
	/**
	 * ��ҳ��ѯ���̣�������������У���������ģ����������״̬�������������id,owner
	 * @param shop
	 * @param rowIndex �ӵڼ���ʼȡ����
	 * @param pageSize ���ص�����
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shop,
			@Param("rowIndex")int rowIndex,@Param("pageSize") int pageSize);
	
	/**
	 * f����queryShopList����
	 * @param shopCondition
	 * @return
	 */
	 int queryShopCount(@Param("shopCondition")Shop shopCondition);
	/**
	 * ͨ�� shop id ��ѯ����
	 * @param shopId
	 * @return
	 */
	Shop queryByShopId(long shopId);
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
