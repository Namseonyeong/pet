package com.ezen.CartService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.Repository.CartRepository;
import com.ezen.entity.Cart;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepository;
	
	
	//카트에물건 담기
	@Override
	public void insertCart(Cart cart) {
		
		cartRepository.save(cart);
	}
	
	//카트목록 불러오기
	@Override
	public List<Cart> getCartList(Cart cart){
		
		return (List<Cart>)cartRepository.findAll();
	}
	
	@Override
	public void deleteCart(Cart cart) {
		
		cartRepository.deleteById(cart.getCartSeq());
	}
}
