package com.ezen.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ezen.entity.Cart;
import com.ezen.entity.Member;


public interface CartRepository extends JpaRepository<Cart, Integer> {

	//카트리스트 불러오기
	@Query("SELECT c FROM Cart c WHERE c.member.memberId=?1 ORDER BY c.cartSeq")
	List<Cart> findCartByMemberId(String memberId);
	
}
