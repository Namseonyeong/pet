package com.ezen.Repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.entity.Cart;
import com.ezen.entity.Member;


public interface CartRepository extends JpaRepository<Cart, Integer> {

	//카트리스트 불러오기
	@Query("SELECT c FROM Cart c WHERE c.member.memberId=?1 AND c.result='0' ORDER BY c.cartSeq")
	List<Cart> findCartByMemberId(String memberId);
	/*
	@Query("SELECT c FROM Cart c WHERE c.cartSeq =?1")
	Cart findCartByCartSeq(int cartSeq);
	*/
	//장바구니 업데이트
	@Modifying
	@Transactional
	@Query(value="UPDATE cart SET result='1' WHERE cart_seq=?1", nativeQuery=true)
	void updateCartResult(int cartSeq);
	
	@Query("SELECT c FROM Cart c WHERE c.cartSeq =?1")
	Cart findCartByCartSeq(Integer cartSeq);
	
	// 주문완료된 데이터 조회
	List<Cart> findByResult(char result);

	@Query("SELECT c FROM Cart c WHERE c.member.memberId=?1")
	List<Cart> findByMemberId(String memberId);
}
