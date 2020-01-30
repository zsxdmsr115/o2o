package com.jiangbin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangbin.dao.ProductDao;
import com.jiangbin.dao.ProductImgDao;
import com.jiangbin.dto.ImageHolder;
import com.jiangbin.dto.ProductExecution;
import com.jiangbin.entity.Product;
import com.jiangbin.entity.ProductImg;
import com.jiangbin.enume.ProductStateEnum;
import com.jiangbin.exception.ProductOperationException;
import com.jiangbin.service.ProductService;
import com.jiangbin.util.ImageUtil;
import com.jiangbin.util.PathUtil;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	@Transactional
	// 1.��������ͼ����ȡ����ͼ���·������ֵ��product
	// 2.��tb_productд����Ʒ��Ϣ����ȡproductId
	// 3.���productId����������Ʒ����ͼ
	// 4.����Ʒ����ͼ�б���������tb_product_img��
	public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
			throws ProductOperationException {
		// ��ֵ�ж�
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			// ����Ʒ������Ĭ������
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			// Ĭ��Ϊ�ϼܵ�״̬
			product.setEnableStatus(1);
			// ����Ʒ����ͼ��Ϊ�������
			if (thumbnail != null) {
				addThumbnail(product, thumbnail);
			}
			try {
				// ������Ʒ��Ϣ
				int effectedNum = productDao.insertProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException("������Ʒʧ��");
				}
			} catch (Exception e) {
				throw new ProductOperationException("������Ʒʧ��:" + e.toString());
			}
			// ����Ʒ����ͼ��Ϊ�������
			if (productImgHolderList != null && productImgHolderList.size() > 0) {
				addProductImgList(product, productImgHolderList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		} else {
			// ����Ϊ���򷵻ؿ�ֵ������Ϣ
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	/**
	 * �������ͼ
	 * 
	 * @param product
	 * @param thumbnail
	 */
	private void addThumbnail(Product product, ImageHolder thumbnail) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateTumbnail(thumbnail, dest);
		product.setImgAddr(thumbnailAddr);
	}

	/**
	 * �������ͼƬ
	 * 
	 * @param product
	 * @param productImgHolderList
	 */
	private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
		// ��ȡͼƬ�洢·��������ֱ�Ӵ�ŵ���Ӧ���̵��ļ��е���
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		// ����ͼƬһ��ȥ��������ӽ�productImgʵ������
		for (ImageHolder productImgHolder : productImgHolderList) {
			String imgAddr = ImageUtil.generateNormalImg(productImgHolder, dest);
			ProductImg productImg = new ProductImg();
			productImg.setImgAddr(imgAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgList.add(productImg);
		}
		// ���ȷʵ����ͼƬ��Ҫ��ӵģ���ִ��������Ӳ���
		if (productImgList.size() > 0) {
			try {
				int effectedNum = productImgDao.batchInsertProductImg(productImgList);
				if (effectedNum <= 0) {
					throw new ProductOperationException("������Ʒ����ͼƬʧ��");
				}
			} catch (Exception e) {
				throw new ProductOperationException("������Ʒ����ͼƬʧ��:" + e.toString());
			}
		}
	}

	public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) {
		// TODO Auto-generated method stub
		return null;
	}
}
