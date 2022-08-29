package com.ezen.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Table(name = "Orders")
@Entity  // 클래스의 테이블
@Data
@EntityListeners(AuditingEntityListener.class)
public class Orders  {

	// 주문
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "order_seq")
	private Integer orderSeq;

	// 수령인
    @Column(name = "order_rce", nullable = false)
    private String orderRce;
   
    // 구매자 전화번호
    @Column(name = "order_tel", nullable = false)
    private String orderTel;
   
    // 구매자 주소
    @Column(name = "order_addr1", nullable = false)
    private String orderAddr1;
   
    // 구매 날짜
    @Column(name = "order_date")
    @CreatedDate
    private LocalDateTime orderDate;
    
    // 멤버 seq
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    // private String memberId;
    
   
}

