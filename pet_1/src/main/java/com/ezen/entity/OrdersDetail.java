package com.ezen.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

	/*
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
*/
	
	// 상품등록번호
	@OneToOne
	@JoinColumn(name = "p_seq")
	private Product product;
	// private int pSeq; 단방향
		
	// 주문번호
	@ManyToOne
	@JoinColumn(name = "order_seq")
	private Orders order;
	// private int orderSeq; 양방향
}