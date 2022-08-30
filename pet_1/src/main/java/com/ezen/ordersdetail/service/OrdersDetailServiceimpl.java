package com.ezen.ordersdetail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ezen.Repository.OrdersDetailRepository;
import com.ezen.entity.OrdersDetail;

@Service
public class OrdersDetailServiceimpl implements OrdersDetailService {
	
	@Autowired
	private OrdersDetailRepository ordersDetailRepository;
	
	// 매출 목록 조회1 (SY/현재 페이징 안됨.)
	@Override
	public Page<OrdersDetail> ordersDetailList(Pageable pageable) {

		return ordersDetailRepository.findAll(pageable);
	}
	
	// 매출 목록 조회2 (SY/현재 페이징 안됨.)
//	@Override
//	public Page<OrdersDetail> ordersDetailSerchList(String searchKeyword, Pageable pageable) {
//
//		return ordersDetailRepository.findByOdSeq(searchKeyword, pageable);
//		
//		ordersDetailRepository.save(orderDetail);
//	}
//	
	@Override
	public void insertOrderDetail(OrdersDetail orderDetail) {
		
		ordersDetailRepository.save(orderDetail);
	}

}
