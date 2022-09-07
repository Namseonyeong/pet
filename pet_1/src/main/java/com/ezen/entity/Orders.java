package com.ezen.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Table(name = "Orders")
@Entity  // 클래스의 테이블
@Data
@EntityListeners(AuditingEntityListener.class)
public class Orders {

	// 주문
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "order_seq")
	private Integer orderSeq;

	// 수령인
    @Column(name = "order_rce")
    private String orderRce;
   
    // 구매자 전화번호
    @Column(name = "order_tel")
    private String orderTel;
   
    // 구매자 주소
    @Column(name = "order_addr1")
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

    public static Orders createOrders(Member member) {
    	Orders order = new Orders();
    	order.setMember(member);
    	return order;
    }

    /**
     *  선영 매출현황 작업으로 인해 임시 주석처리
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    List<OrdersDetail> orderDetailList = new ArrayList<>();
    
	/**
	 * 선영 매출현황 작업으로 변경한 엔티티
	 */
    
 /*
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "od_seq")
	private OrdersDetail ordersDetail;
	*/
	
	
}

