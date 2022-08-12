package com.ezen.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.CartService.CartService;
import com.ezen.Repository.OrdersRepository;
import com.ezen.entity.Cart;
import com.ezen.entity.Orders;

@Service(OrdersService)
public class OrderServiceImpl {

	
	@Autowired
	private Cart cart;
	
	@Autowired
	private Orders order;
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	private CartService cartservice;
	
	public void inserOrder(Orders order) {
		
		ordersRepository.save(order);
		
		List<Cart> listCart = cartservice.getCartList(order.getmember_id());
	}
	
	
}
