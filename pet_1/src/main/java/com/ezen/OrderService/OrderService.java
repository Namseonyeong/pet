package com.ezen.OrderService;

import java.util.List;

import com.ezen.Repository.OrdersRepository;
import com.ezen.entity.Cart;
import com.ezen.entity.Orders;

public interface OrderService {
	
	 int insertOrder(Orders orders);
	 
	 List<Orders> findOrderByMemberId(String MemberId);

	void insertInfo(Orders order);

	//void deleteOrder(Integer orderSeq);
	 
	 //void insertOrder(Orders orders);
	 
	 //void updateResult(Integer orderSeq);
}
