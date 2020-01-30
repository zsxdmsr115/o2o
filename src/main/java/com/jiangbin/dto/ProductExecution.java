package com.jiangbin.dto;

import java.util.List;

import com.jiangbin.entity.Product;
import com.jiangbin.entity.ProductCategory;
import com.jiangbin.enume.ProductCategoryStateEnum;
import com.jiangbin.enume.ProductStateEnum;

public class ProductExecution {

	// 结果状态
	private int state;
	// 状态标识
	private String stateInfo;

	private List<Product> productList;
	private Product product;
	private ProductStateEnum penum;
	public ProductExecution() {

	}

	// 操作失败的时候使用的构造器
	public ProductExecution(ProductCategoryStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 操作成功的时候使用的构造器
	public ProductExecution(ProductCategoryStateEnum stateEnum, List<Product> productList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productList = productList;
	}
	//操作成功的构造器
	public ProductExecution(ProductStateEnum stateEnum, Product product) {
		this.state = stateEnum.getState();
		this.product = product;
	}
	//操作失败的构造器
	public ProductExecution(ProductStateEnum penum) {
		this.penum=penum;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public List<Product> getProductCategoryList() {
		return productList;
	}

	public void setProductCategoryList(List<Product> productList) {
		this.productList = productList;
	}

}
