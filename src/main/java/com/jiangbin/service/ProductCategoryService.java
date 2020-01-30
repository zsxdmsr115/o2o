package com.jiangbin.service;

import java.util.List;

import com.jiangbin.dto.ProductCategoryExecution;
import com.jiangbin.entity.ProductCategory;
import com.jiangbin.exception.ProductCategoryOperationException;

public interface ProductCategoryService {
	/**
	 * 查询指定某个店铺下的所有商品类别信息
	 * @param long shopId
	 * @return List<ProductCategory>
	 */
 List<ProductCategory> getProductCategoryList(long shopId);
 
 /**
	 * 批量添加商铺类别
	 * @param productCategory
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException;
	/**
	 * 将此类别下的商品的类别ID设置为空，在删除该商品类别
	 * @param producateId
	 * @param shopId
	 * @return ProductCategoryExecution
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution deleteProductCategory(long producateId,long shopId)throws ProductCategoryOperationException;
}
