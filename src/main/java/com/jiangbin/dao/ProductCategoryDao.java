package com.jiangbin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jiangbin.entity.ProductCategory;

public interface ProductCategoryDao {
	/**
	 * 通过shop id查询店铺商品类别
	 * @param shopId
	 * @return List<ProductCategory>
	 */
	List<ProductCategory> queryProductCategoryList(long shopId);
	/**
	 * 批量新增商品类别
	 * @param plist
	 * @return
	 */
	int batchInsertProductCategory(List<ProductCategory> plist);
	/**
	 * 删除指定商品类别
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * @return effectedNum
	 */
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
	
}
