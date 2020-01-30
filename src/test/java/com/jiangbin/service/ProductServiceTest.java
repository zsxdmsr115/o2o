package com.jiangbin.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jiangbin.BaseTest;
import com.jiangbin.dto.ImageHolder;
import com.jiangbin.dto.ProductExecution;
import com.jiangbin.entity.Product;
import com.jiangbin.entity.ProductCategory;
import com.jiangbin.entity.Shop;
import com.jiangbin.enume.ProductStateEnum;
import com.jiangbin.exception.ShopOperationException;


public class ProductServiceTest extends BaseTest {
	@Autowired
	private ProductService productService;
	@Test
	public void testAddProduct() throws ShopOperationException, FileNotFoundException {
		// ����shopIdΪ1��productCategoryIdΪ1����Ʒʵ���������Ա������ֵ
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(1L);
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(17L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("������Ʒ1");
		product.setProductDesc("������Ʒ1");
		product.setPriority(20);
		product.setCreateTime(new Date());
		product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
		// ��������ͼ�ļ���
		File thumbnailFile = new File("C:/Users/zsxdmsr115/Desktop/1.jpg");
		InputStream is = new FileInputStream(thumbnailFile);
		ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
		// ����������Ʒ����ͼ�ļ�������������ӵ�����ͼ�б���
		File productImg1 = new File("C:/Users/zsxdmsr115/Desktop/1.jpg");
		InputStream is1 = new FileInputStream(productImg1);
		File productImg2 = new File("C:/Users/zsxdmsr115/Desktop/2.jpg");
		InputStream is2 = new FileInputStream(productImg2);
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		productImgList.add(new ImageHolder(productImg1.getName(), is1));
		productImgList.add(new ImageHolder(productImg2.getName(), is2));
		// �����Ʒ����֤
		ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
		assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
	}

	@Test
	@Ignore
	public void testModifyProduct() throws ShopOperationException, FileNotFoundException {
		// ����shopIdΪ1��productCategoryIdΪ1����Ʒʵ���������Ա������ֵ
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(1L);
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(17L);
		product.setProductId(1L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("��ʽ����Ʒ");
		product.setProductDesc("��ʽ����Ʒ");
		// ��������ͼ�ļ���
		File thumbnailFile = new File("/Users/baidu/work/image/ercode.jpg");
		InputStream is = new FileInputStream(thumbnailFile);
		ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
		// ����������Ʒ����ͼ�ļ�������������ӵ�����ͼ�б���
		File productImg1 = new File("/Users/baidu/work/image/xiaohuangren.jpg");
		InputStream is1 = new FileInputStream(productImg1);
		File productImg2 = new File("/Users/baidu/work/image/dabai.jpg");
		InputStream is2 = new FileInputStream(productImg2);
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		productImgList.add(new ImageHolder(productImg1.getName(), is1));
		productImgList.add(new ImageHolder(productImg2.getName(), is2));
		// �����Ʒ����֤
		ProductExecution pe = productService.modifyProduct(product, thumbnail, productImgList);
		assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
	}

}
