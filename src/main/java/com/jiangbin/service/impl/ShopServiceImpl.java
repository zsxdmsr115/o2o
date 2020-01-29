package com.jiangbin.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.Date;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangbin.dao.ShopDao;
import com.jiangbin.dto.ShopExecution;
import com.jiangbin.entity.Shop;
import com.jiangbin.enume.ShopStateEnum;
import com.jiangbin.exception.ShopOperationException;
import com.jiangbin.service.ShopService;
import com.jiangbin.util.ImageUtil;
import com.jiangbin.util.PathUtil;
@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;
	@Transactional
	public ShopExecution addShop(Shop shop, InputStream shopImgInputstrem,String fileName) {
		//��ֵ�ж�
		if(shop==null){
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			//��������Ϣ��ֵ��ʼֵ
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			//��ӵ�����Ϣ
			int effectedNum = shopDao.insertShop(shop);
			if(effectedNum<=0){
				throw new ShopOperationException("���̴���ʧ��");
			}else{
				if(shopImgInputstrem!=null){
					//�洢ͼƬ
					try {
						addShopImg(shop,shopImgInputstrem,fileName);
					} catch (Exception e) {
						throw new ShopOperationException("addShopImg error:"+e.getMessage());
					}
					//
					effectedNum=shopDao.updateShop(shop);
					if(effectedNum<=0){
						throw new ShopOperationException("����ͼƬ��ַʧ��");
					}
				}
			}
			
		} catch (Exception e) {
			throw new ShopOperationException("addshop error"+e.getMessage());
		}
		return  new ShopExecution(ShopStateEnum.CHECK,shop);
	}
	private void addShopImg(Shop shop, InputStream shopImgInputStream,String fileName) {
		//��ȡshopͼƬĿ¼�������·��
		String desst = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateTumbnail(shopImgInputStream,fileName, desst);
		shop.setShopImg(shopImgAddr);
	}

}
