package com.jiangbin.exception;

public class ProductOperationException extends RuntimeException{
	private static final long serialVersionUID = 8084653668269166390L;
	public ProductOperationException(String msg){
		super(msg);
	}

}
