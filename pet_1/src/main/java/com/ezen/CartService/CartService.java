package com.ezen.CartService;

import java.util.List;

import com.ezen.entity.Cart;

public interface CartService {
	
	void insertCart(Cart cart);
	
	List<Cart> getCartList(Cart cart);

	void deleteCart(Cart cart);
	
	
}
