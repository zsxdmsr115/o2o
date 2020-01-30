package com.jiangbin.dto;

import java.util.List;

import com.jiangbin.entity.Product;
import com.jiangbin.entity.ProductCategory;
import com.jiangbin.enume.ProductCategoryStateEnum;
import com.jiangbin.enume.ProductStateEnum;

public class ProductExecution {

	// ���״̬
	private int state;
	// ״̬��ʶ
	private String stateInfo;

	private List<Product> productList;
	private Product product;
	private ProductStateEnum penum;
	public ProductExecution() {

	}

	// ����ʧ�ܵ�ʱ��ʹ�õĹ�����
	public ProductExecution(ProductCategoryStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// �����ɹ���ʱ��ʹ�õĹ�����
	public ProductExecution(ProductCategoryStateEnum stateEnum, List<Product> productList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productList = productList;
	}
	//�����ɹ��Ĺ�����
	public ProductExecution(ProductStateEnum stateEnum, Product product) {
		this.state = stateEnum.getState();
		this.product = product;
	}
	//����ʧ�ܵĹ�����
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
