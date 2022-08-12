package com.ezen.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.entity.Cart;
import com.ezen.entity.Member;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

	List<Cart> listCart(Member member);
	
	
	
}
