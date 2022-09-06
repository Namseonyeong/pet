package com.ezen.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ezen.entity.ODSalesInterface;
import com.ezen.entity.OrderDetailInterface;
import com.ezen.entity.OrdersDetail;

public interface OrdersDetailRepository extends JpaRepository<OrdersDetail, Integer>{
	
	@Query("SELECT OD FROM OrdersDetail OD WHERE OD.odDate between ?1 and ?2 and result='1' ORDER BY OD.odSeq")
	Page<OrdersDetail> findAllByOdDateBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
	
	@Query("SELECT OD FROM OrdersDetail OD WHERE OD.odDate between ?1 and ?2 and OD.product.pKind=?3 and result='1' ORDER BY OD.odSeq")
	Page<OrdersDetail> findAllByOdDateBetweenAndPkind(LocalDateTime start, LocalDateTime end, String searchKeyword, Pageable pageable);
	
	//뷰 생성
	@Query(value="select OD.ORDER_SEQ, P.P_PATH, P.P_NAME, OD.QUANTITY, P.PRICE2, SUM(OD.QUANTITY * P.PRICE2 ) OVER() AS TOTAL_PRICE, OD.OD_DATE from orders_detail OD, product p WHERE OD.P_SEQ=P.P_SEQ AND OD.result='0' AND OD.ORDER_SEQ=?1", nativeQuery=true)
	public List<OrderDetailInterface> findOrderlist(int orderSeq);
	
	// 대시보드 차트 매출 데이터 조회
	@Query(value = "SELECT P.P_KIND, SUM(OD.QUANTITY) AS QUANTITY, SUM(OD.QUANTITY * P.PRICE3) AS INCOME FROM ORDERS_DETAIL OD, PRODUCT P WHERE OD.P_SEQ=P.P_SEQ AND RESULT = '1' GROUP BY P.P_KIND", nativeQuery = true)
	public List<ODSalesInterface> findOrderDetailChartData();

	// 결제완료 데이터 조회
	public List<OrdersDetail> findByResult(char result);
	
	
}

	
