package com.jiangbin.service;

import java.io.File;
import java.io.InputStream;

import com.jiangbin.dto.ImageHolder;
import com.jiangbin.dto.ShopExecution;
import com.jiangbin.entity.Shop;
import com.jiangbin.exception.ShopOperationException;

public interface ShopService {
	/**
	 * 根据shopCondition分页返回相应店铺列表
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
	/**
	 * 更新店铺信息,包括对图片的处理
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileNma
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution modifyShop(Shop shop,ImageHolder thumbnail) throws ShopOperationException;
	/**
	 *  通过店铺id获取店铺信息
	 * @param shopId
	 * @return Shop
	 */
	Shop getByShopId(long shopId);
	
	/**
	 *  添加店铺，包括对图片的处理
	 * @param shop
	 * @param shopImgInput
	 * @param fileName
	 * @return
	 */
	ShopExecution addShop(Shop shop,ImageHolder thumbnail);
}
