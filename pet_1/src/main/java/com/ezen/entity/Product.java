package com.ezen.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.ezen.BaseTimeEntity;

import lombok.Data;


@Table(name = "Product")
@Entity  // 클래스의 테이블
@Data
@EntityListeners(AuditingEntityListener.class)
public class Product extends BaseTimeEntity {

	// 상품 등록
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "p_seq")
	private Integer pSeq; 

	// 상품 분류
	@Column(name = "p_kind")
	private String pKind;  
	
	// 상품명
	@Column(name = "p_name", nullable = false)
	private String pName; 
	
	// 원가
	@Column(name = "price1", columnDefinition="number(5) default 0", nullable = false)
	private Integer price1; 
	
	// 판매가
	@Column(name = "price2", columnDefinition="number(5) default 0", nullable = false)
	private Integer price2; 
	
	// 순수익
	@Column(name = "price3", columnDefinition="number(5) default 0")
	private Integer price3; 
	
	// 상세설명
	@Column(name = "p_content", length = 1000, nullable = false)
	private String pContent; 
	
	// 상품 이미지
	@Column(name = "p_image", length = 1000)
	private String pImage; 
	
	// 이미지 이름
	@Column(name = "p_path")
	private String pPath; 
	
	//cart 와 1:1조인
	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	// 오더디테일test
//	@OneToMany(mappedBy = "product", fetch=FetchType.EAGER)
//	private List<OrdersDetailSy> ordersDetailSyList = new ArrayList<OrdersDetailSy>();
	
	
	// 리스트로 상품분류 페이징test
	
//	@OneToMany(mappedBy = "product")
//	private List pKind;
	
//	@Column(name = "p_kind")
//	@OneToMany(mappedBy = "product")
//	private List<OrdersDetailSy> pKind;  
}


