
package com.ezen.ordersdetail.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ezen.entity.OrderDetailInterface;
import com.ezen.entity.OrdersDetail;


public interface OrdersDetailService {

	// 매출 목록 조회1 (SY/현재 페이징 안됨.)
	public Page<OrdersDetail> ordersDetailList(Pageable pageable);
	
	// 매출 목록 조회1 (SY/현재 페이징 안됨.)
//	public Page<OrdersDetail> ordersDetailSerchList(String searchKeyword, Pageable pageable);
	
	//상세주문정보에 저장
	void insertOrderDetail(OrdersDetail orderDetail);
	
	//뷰생성
	List<OrderDetailInterface> findOrderlist(int orderSeq);
	
	//결제완료시업데이트
	void updateOrderResult(Integer odSeq);
	
}
