package com.ezen.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.Repository.OrdersRepository;
import com.ezen.entity.Cart;
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
		List<Orders> list = orderRepo.findOrderByMemberId(MemberId);
		System.out.println("list == > " + list);
				
		return list;
	}
	
	/*
	//주문테이블에 담기
	@Override
	public void insertOrder(Orders orders) {

		orderRepo.save(orders);
	
	}
	
	*/
	
	/*
	//주문테이블 결제하기
	@Override
	public void updateResult(Integer orderSeq) {
		
		orderRepo.updateResult(orderSeq);
	}
	*/
	
	//주문페이지에서 정보입력란에 입력한 정보 저장
	@Override
	public void insertInfo(Orders order) {
		
		orderRepo.save(order);
	}
	/*
	@Override
	public void deleteOrder(Integer orderSeq) {
		
		orderRepo.deleteById(orderSeq);
	}
	*/	
		
}
