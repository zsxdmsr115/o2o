package com.jiangbin.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangbin.dao.ShopDao;
import com.jiangbin.dto.ImageHolder;
import com.jiangbin.dto.ShopExecution;
import com.jiangbin.entity.Shop;
import com.jiangbin.enume.ShopStateEnum;
import com.jiangbin.exception.ShopOperationException;
import com.jiangbin.service.ShopService;
import com.jiangbin.util.ImageUtil;
import com.jiangbin.util.PageCalcuator;
import com.jiangbin.util.PathUtil;
@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;
	@Transactional
	public ShopExecution addShop(Shop shop, ImageHolder thumbnail) {
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
				if(thumbnail.getImage()!=null){
					//�洢ͼƬ
					try {
						addShopImg(shop,thumbnail);
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
	private void addShopImg(Shop shop, ImageHolder thumail) {
		//��ȡshopͼƬĿ¼�������·��
		String desst = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateTumbnail(thumail, desst);
		shop.setShopImg(shopImgAddr);
	}
	public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) {
		if(shop==null || shop.getShopId()==null){
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}else{
			try {
				//1:�ж��Ƿ���Ҫ����ͼƬ
				if(thumbnail.getImage()!=null && thumbnail.getImageName()!=null && !"".equals(thumbnail.getImageName())){
					Shop tempShop = shopDao.queryByShopId(shop.getShopId());
					if(tempShop.getShopImg()!=null){
						ImageUtil.deletFileOrPath(tempShop.getShopImg());//ɾ��ԭ����ͼƬ
					}
					addShopImg(shop, thumbnail);//����µ�ͼƬ
				}
				//2:���µ�����Ϣ
				shop.setLastEditTime(new Date());
				int effecteNum = shopDao.updateShop(shop);
				if(effecteNum<=0){
					return new ShopExecution(ShopStateEnum.INNER_ERROR);
				}else{
					shop=shopDao.queryByShopId(shop.getShopId());
					return new ShopExecution(ShopStateEnum.SUCCESS,shop);
				}
			} catch (Exception e) {
				throw new ShopOperationException("modify shop"+e.getMessage());
			}
		}
	}
	public Shop getByShopId(long shopId) {
		return shopDao.queryByShopId(shopId);
	}
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalcuator.calculateRowIndex(pageIndex, pageSize);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
		int count =shopDao.queryShopCount(shopCondition);
		ShopExecution se = new ShopExecution();
		if(shopList!=null){
			se.setShopList(shopList);
			se.setCount(count);
		}else{
			se.setState(ShopStateEnum.INNER_ERROR.getState());
		}
			return se;
	}

}
