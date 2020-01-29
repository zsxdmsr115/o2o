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
		//空值判断
		if(shop==null){
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			//给店铺信息赋值初始值
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			//添加店铺信息
			int effectedNum = shopDao.insertShop(shop);
			if(effectedNum<=0){
				throw new ShopOperationException("店铺创建失败");
			}else{
				if(shopImgInputstrem!=null){
					//存储图片
					try {
						addShopImg(shop,shopImgInputstrem,fileName);
					} catch (Exception e) {
						throw new ShopOperationException("addShopImg error:"+e.getMessage());
					}
					//
					effectedNum=shopDao.updateShop(shop);
					if(effectedNum<=0){
						throw new ShopOperationException("更新图片地址失败");
					}
				}
			}
			
		} catch (Exception e) {
			throw new ShopOperationException("addshop error"+e.getMessage());
		}
		return  new ShopExecution(ShopStateEnum.CHECK,shop);
	}
	private void addShopImg(Shop shop, InputStream shopImgInputStream,String fileName) {
		//获取shop图片目录的相对子路径
		String desst = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateTumbnail(shopImgInputStream,fileName, desst);
		shop.setShopImg(shopImgAddr);
	}

}
