package com.jiangbin.exception;

public class ProductCategoryOperationException extends RuntimeException {
	private static final long serialVersionUID = -2535033721970298109L;
	public ProductCategoryOperationException(String msg) {
		super(msg);
	}
}
