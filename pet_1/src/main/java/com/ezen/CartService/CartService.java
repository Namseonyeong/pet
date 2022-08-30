package com.ezen.CartService;

import java.util.List;

import com.ezen.entity.Cart;

public interface CartService {
	
	void insertCart(Cart cart);
	
	List<Cart> findCartByMemberId(String MemberId);

	void deleteCart(Integer cartSeq);
	
	//오더디테일스구현을 위한 작업 /카트시퀀스번호 하나씩 조회하기(OrderDetail에 사용)
	Cart findCartByCartSeq(int cartSeq);


	
}
