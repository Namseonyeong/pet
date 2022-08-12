package com.ezen.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

// 구매 이력
@Table(name = "Orders_Detail")
@Entity  // 클래스의 테이블
@Data
public class OrdersDetail {

	// 구매 이력
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="od_seq")
	private Integer odSeq;
	
	// 총 판매 수량
	@Column(name = "quantity", nullable = false, columnDefinition="number(5) default 4")
	private Integer quantity;
	
	// 상품등록번호
	@OneToMany
	@JoinColumn(name = "p_seq")
    private List<Product> ProductList = new ArrayList<Product>();
	// private int pSeq;

	// 주문번호
	@OneToMany
	@JoinColumn(name = "order_seq")
	private List<Orders> OrderList = new ArrayList<Orders>();
	// private int orderSeq;
	
}

