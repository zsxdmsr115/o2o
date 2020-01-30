package com.jiangbin.dao;

import java.util.List;

import com.jiangbin.entity.ProductImg;

public interface ProductImgDao {
	/**
	 * �г�ĳ����Ʒ������ͼ�б�
	 * 
	 * @param productId
	 * @return
	 */
	List<ProductImg> queryProductImgList(long productId);

	/**
	 * ���������Ʒ����ͼƬ
	 * 
	 * @param productImgList
	 * @return
	 */
	int batchInsertProductImg(List<ProductImg> productImgList);

	/**
	 * ɾ��ָ����Ʒ�µ���������ͼ
	 * 
	 * @param productId
	 * @return
	 */
	int deleteProductImgByProductId(long productId);
}
