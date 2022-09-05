package com.ezen.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Cart")
@Data
public class Cart {
			
	 // 장바구니
	   @Id
	   @GeneratedValue(strategy = GenerationType.SEQUENCE)
	   @Column(name = "cart_seq")
	   private Integer cartSeq;
		    
	   //수량
	   @Column(name="cart_strock", nullable = false)
	   private Integer cartStrock;
		    
	   // 멤버 seq
	   @OneToOne(fetch = FetchType.LAZY)
	   @JoinColumn(name = "member_id")
	   private Member member;
	
	   public static Cart createCart(Member member) {
		   Cart cart = new Cart();
		   cart.setMember(member);
		   return cart;
	   }
	   
	   
	   
//	   // 멤버 seq
//	   @OneToOne(fetch = FetchType.LAZY)
//	   @JoinColumn(name = "member_id")
//	   private Member member;
//	
//	   
//	   
//	   
//	   
//	   
//	   public static Cart createCart(Member member) {
//		   Cart cart = new Cart();
//		   cart.setMember(member);
//		   return cart;
//	   }
//	   
	   //product 와 1:1 조인
	   @OneToOne
	   @JoinColumn(name="product_id")
	   private Product product;
	   
	   
	   private Integer totalprice;
	   
	   //장바구니에서 주문완료시 '1' 로 업데이트
	   @Column(columnDefinition ="number(1) default '0'")
	   private char result;
}
