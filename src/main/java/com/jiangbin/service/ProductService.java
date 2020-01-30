package com.jiangbin.service;

import java.util.List;

import com.jiangbin.dto.ImageHolder;
import com.jiangbin.dto.ProductExecution;
import com.jiangbin.entity.Product;
import com.jiangbin.exception.ProductOperationException;

public interface ProductService {
	/**
	 * �����Ʒ��Ϣ�Լ�ͼƬ����
	 * 
	 * @param product
	 * @param thumbnail
	 * @param productImgs
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution addProduct(Product product,ImageHolder imageHolder,List<ImageHolder> productImgList)
			throws ProductOperationException;

	ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList);
}
