package com.jiangbin.service;

import java.io.File;
import java.io.InputStream;

import com.jiangbin.dto.ShopExecution;
import com.jiangbin.entity.Shop;
import com.jiangbin.exception.ShopOperationException;

public interface ShopService {
	/**
	 * ����shopCondition��ҳ������Ӧ�����б�
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
	/**
	 * ���µ�����Ϣ
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileNma
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution modifyShop(Shop shop,InputStream shopImgInputStream,String fileNma) throws ShopOperationException;
	/**
	 *  ͨ������id��ȡ������Ϣ
	 * @param shopId
	 * @return Shop
	 */
	Shop getByShopId(long shopId);
	
	/**
	 *  ��ӵ���
	 * @param shop
	 * @param shopImgInput
	 * @param fileName
	 * @return
	 */
	ShopExecution addShop(Shop shop,InputStream shopImgInput,String fileName);
}
