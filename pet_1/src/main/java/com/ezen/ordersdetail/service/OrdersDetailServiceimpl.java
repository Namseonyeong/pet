package com.ezen.ordersdetail.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ezen.Repository.OrderDetailInterfaceRepository;
import com.ezen.Repository.OrdersDetailRepository;
import com.ezen.entity.ODSalesInterface;
import com.ezen.entity.OrderDetailInterface;
import com.ezen.entity.OrdersDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrdersDetailServiceimpl implements OrdersDetailService {
	@Autowired
	private OrderDetailInterfaceRepository odInRepo;
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
	
	//뷰생성
	@Override
	public List<OrderDetailInterface> findOrderlist(int orderSeq) {
		
		return ordersDetailRepository.findOrderlist(orderSeq);
	}
	
	//결제 완료 시 업데이트
	@Override
	public void updateOrderResult(Integer orderSeq) {
		
		odInRepo.updateOrderResult(orderSeq);
	}
	
	// 차트에 대한 매출관련 데이터 인터페이스 기반
	@Override
	public String findOrderDetailChartData() {
		String json = null;

		List<ODSalesInterface> list = ordersDetailRepository.findOrderDetailChartData();

		// request.setAttribute("list", list);
		HashMap<String, List<ODSalesInterface>> map = new HashMap<String, List<ODSalesInterface>>();
		map.put("list", list);

		try {
			json = new ObjectMapper().writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return json;
	}
		
}
