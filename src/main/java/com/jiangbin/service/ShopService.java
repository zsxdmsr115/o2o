package com.jiangbin.service;

import java.io.File;
import java.io.InputStream;

import com.jiangbin.dto.ShopExecution;
import com.jiangbin.entity.Shop;

public interface ShopService {
	ShopExecution addShop(Shop shop,InputStream shopImgInput,String fileName);
}
