package com.ezen.entity;

import java.time.LocalDateTime;

// 인터페이스 기반 프로젝션 방법
public interface OrderDetailInterface {

	Integer getOrder_seq();

	String getP_path();

	String getP_name();

	Integer getQuantity();

	Integer getPrice2();

	Integer getTotal_price();
	
	LocalDateTime getOd_date();
}
