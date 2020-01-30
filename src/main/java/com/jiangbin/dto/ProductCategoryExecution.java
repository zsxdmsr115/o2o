package com.jiangbin.dto;

import java.util.List;

import com.jiangbin.entity.ProductCategory;
import com.jiangbin.enume.ProductCategoryStateEnum;


public class ProductCategoryExecution {
	// ���״̬
	private int state;
	// ״̬��ʶ
	private String stateInfo;

	private List<ProductCategory> productCategoryList;

	public ProductCategoryExecution() {

	}

	// ����ʧ�ܵ�ʱ��ʹ�õĹ�����
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// �����ɹ���ʱ��ʹ�õĹ�����
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum, List<ProductCategory> productCategoryList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productCategoryList = productCategoryList;
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

	public List<ProductCategory> getProductCategoryList() {
		return productCategoryList;
	}

	public void setProductCategoryList(List<ProductCategory> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}

}
