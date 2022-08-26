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
	private CartRepository cartRepo;
	
	//카트에물건 담기
	@Override
	public void insertCart(Cart cart) {
		
		cartRepo.save(cart);
	}
	
	//카트목록 불러오기
	@Override
	public List<Cart> findCartByMemberId(String MemberId) {
		
		return (List<Cart>)cartRepo.findCartByMemberId(MemberId);
	}
	
	//카트목록 삭제하기
	@Override
	public void deleteCart(Integer cartSeq) {
		
		cartRepo.deleteById(cartSeq);
	}
	
}
