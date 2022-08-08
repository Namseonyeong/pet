package com.ezen.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity  // 클래스의 테이블
@Data
public class Product {

	// 상품 등록
	// ID = PK 키
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer p_seq; // 상품 등록번호
//	@Column (length = 1)
	private String p_kind;  // 상품 분류
	@Column(length = 100)
	private String p_name; // 상품명
	private Integer price1; // 원가
	private Integer price2; // 판매가
	private Integer price3; // 순수익
	private String p_content; // 상세설명
	private String p_image; // 상품 이미지
	private String p_path; // 이미지 이름
	
}
