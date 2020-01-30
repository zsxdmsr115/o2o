package com.jiangbin.service;

import java.util.List;

import com.jiangbin.dto.ProductCategoryExecution;
import com.jiangbin.entity.ProductCategory;
import com.jiangbin.exception.ProductCategoryOperationException;

public interface ProductCategoryService {
	/**
	 * ��ѯָ��ĳ�������µ�������Ʒ�����Ϣ
	 * @param long shopId
	 * @return List<ProductCategory>
	 */
 List<ProductCategory> getProductCategoryList(long shopId);
 
 /**
	 * ��������������
	 * @param productCategory
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException;
	/**
	 * ��������µ���Ʒ�����ID����Ϊ�գ���ɾ������Ʒ���
	 * @param producateId
	 * @param shopId
	 * @return ProductCategoryExecution
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution deleteProductCategory(long producateId,long shopId)throws ProductCategoryOperationException;
}
