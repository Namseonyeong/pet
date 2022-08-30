package com.ezen.CartService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ezen.Repository.CartRepository;
import com.ezen.Repository.ProductRepository;
import com.ezen.entity.Cart;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CartRepository cartRepository;
	
	//카트에물건 담기
	@Override
	public void insertCart(Cart cart) {
		
		cartRepository.save(cart);
	}
	
	//카트목록 불러오기
	@Override
	public List<Cart> findCartByMemberId(String MemberId) {
		
		return (List<Cart>)cartRepository.findCartByMemberId(MemberId);
	}
	
	//카트목록 삭제하기
	@Override
	public void deleteCart(Integer cartSeq) {
		
		cartRepository.deleteById(cartSeq);
	}
	
	//카트시퀀스번호 하나씩 조회하기(OrderDetail에 사용)
	@Override
	public Cart findCartByCartSeq(int cartSeq) {
		
		
		return cartRepository.findCartByCartSeq(cartSeq);
		
	}
	
}





















