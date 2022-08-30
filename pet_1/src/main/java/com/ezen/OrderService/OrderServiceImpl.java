package com.ezen.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.Repository.OrdersRepository;
import com.ezen.entity.Orders;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrdersRepository orderRepo;

	//주문테이블에 담기
	@Override
	public int insertOrder(Orders orders) {
		
		Orders result = orderRepo.save(orders);
		
		return result.getOrderSeq();
	}
	
	//주문목록 불러오기(카트리스트와 동일)
	@Override
	public List<Orders> findOrderByMemberId(String MemberId){
	
		return (List<Orders>)orderRepo.findOrderByMemberId(MemberId);
	}
	
	
	
	
}
