package com.ezen.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.entity.OrderDetailInterface;
import com.ezen.entity.OrdersDetail;

public interface OrderDetailInterfaceRepository extends JpaRepository<OrdersDetail, Integer>{
	
	//뷰 생성
	@Query(value="select OD.ORDER_SEQ, P.P_PATH, P.P_NAME, OD.QUANTITY, P.PRICE2, SUM(OD.QUANTITY * P.PRICE2 ) OVER() AS TOTAL_PRICE from orders_detail OD, product P WHERE OD.P_SEQ=P.P_SEQ AND OD.result='0' AND OD.ORDER_SEQ=?1", nativeQuery=true)
	public List<OrderDetailInterface> findOrderlist(int orderSeq);
	
	//결제완료시업데이트
	@Modifying
	@Transactional
	@Query(value="UPDATE orders_detail SET result='1' WHERE order_Seq=?1", nativeQuery=true)
	void updateOrderResult(Integer orderSeq);
}
