package com.jiangbin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jiangbin.entity.ProductCategory;

public interface ProductCategoryDao {
	/**
	 * ͨ��shop id��ѯ������Ʒ���
	 * @param shopId
	 * @return List<ProductCategory>
	 */
	List<ProductCategory> queryProductCategoryList(long shopId);
	/**
	 * ����������Ʒ���
	 * @param plist
	 * @return
	 */
	int batchInsertProductCategory(List<ProductCategory> plist);
	/**
	 * ɾ��ָ����Ʒ���
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * @return effectedNum
	 */
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
	
}
