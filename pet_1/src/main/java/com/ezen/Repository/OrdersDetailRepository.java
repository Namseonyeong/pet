package com.ezen.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ezen.entity.OrderDetailInterface;
import com.ezen.entity.OrdersDetail;

public interface OrdersDetailRepository extends JpaRepository<OrdersDetail, Integer>{
	
	Page<OrdersDetail> findAllByOdDateBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
	
	//뷰 생성
	@Query(value="select OD.ORDER_SEQ, P.P_PATH, P.P_NAME, OD.QUANTITY, P.PRICE2 from orders_detail OD, product p WHERE OD.P_SEQ=P.P_SEQ AND OD.result='0' AND OD.ORDER_SEQ=?1", nativeQuery=true)
	public List<OrderDetailInterface> findOrderlist(int orderSeq);
}

	
