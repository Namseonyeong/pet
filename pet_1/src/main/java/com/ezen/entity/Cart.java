package com.ezen.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	   @ManyToOne
	   @JoinColumn(name = "member_id")
	   private Member member;
		   
	   
	   // 상품등록번호
	   @ManyToOne
	   @JoinColumn(name="p_seq")
	   private Product product;
	   
	   
//	   // 멤버 seq
//	   @OneToOne(fetch = FetchType.LAZY)
//	   @JoinColumn(name = "member_id")
//	   private Member member;
	
//	   
//	   public static Cart createCart(Member member) {
//		   Cart cart = new Cart();
//		   cart.setMember(member);
//		   return cart;
//	   }
	   
//	   //product 와 1:1 조인
//	   @OneToOne
//	   @JoinColumn(name="product_id")
//	   private Product product;
	   
	   
}
