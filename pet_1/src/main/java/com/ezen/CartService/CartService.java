package com.ezen.CartService;

import java.util.List;

import com.ezen.entity.Cart;

public interface CartService {
	
	void insertCart(Cart cart);
	
	List<Cart> findCartByMemberId(String MemberId);

	void deleteCart(Integer cartSeq);

	

	
}
