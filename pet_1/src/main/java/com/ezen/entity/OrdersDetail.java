package com.ezen.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

// 구매 이력
@Table(name = "Orders_Detail")
@Entity  // 클래스의 테이블
@Data
@EntityListeners(AuditingEntityListener.class)
public class OrdersDetail {

	// 구매 이력
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="od_seq")
	private Integer odSeq;
	
	// 총 판매 수량
	@Column(name = "quantity", nullable = false, columnDefinition="number(5) default '4'")
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
		
	/**
	 * 선영 매출현황 작업으로 인해 임시 주석처리
	 */
//	// 주문번호
//	@ManyToOne
//	@JoinColumn(name = "order_seq")
//	private Orders order;
//	// private int orderSeq; 양방향
	
	/**
	 * 선영 매출현황 작업으로 변경한 엔티티
	 */
	@OneToOne
	@JoinColumn(name = "order_seq")
	private Orders order;
	
    // 결제 날짜
    @Column(name = "od_date")
    @CreatedDate
    private LocalDateTime odDate;
    
    //결제완료시 '1'로 업데이트
    @Column(columnDefinition ="number(1) default 0")
    private char result;
    
    
}