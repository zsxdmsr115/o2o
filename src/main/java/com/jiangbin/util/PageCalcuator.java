package com.jiangbin.util;

public class PageCalcuator {
	public static int calculateRowIndex(int pageIndex,int pageSize){
		return (pageIndex>0)?(pageIndex-1)*pageSize:0;
	}
}
