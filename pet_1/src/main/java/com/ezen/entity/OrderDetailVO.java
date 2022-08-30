package com.ezen.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetailVO {
	
	private String pImage;
	private String pName;
	private Integer cartStrock;
	private Integer price2;
	private Integer totalprice;
	
}
