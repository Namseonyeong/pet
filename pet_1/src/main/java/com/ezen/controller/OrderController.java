package com.ezen.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.CartService.CartService;
import com.ezen.OrderService.OrderService;
import com.ezen.Repository.ProductRepository;
import com.ezen.entity.Cart;
import com.ezen.entity.Member;
import com.ezen.entity.Orders;
import com.ezen.entity.OrdersDetail;
import com.ezen.entity.Product;
import com.ezen.ordersdetail.service.OrdersDetailService;
import com.ezen.product.service.ProductService;

@Controller
public class OrderController {
	
	@Autowired
	private OrdersDetailService odService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private ProductService productService;
	@Autowired
	private CartService cartService;
	
	@RequestMapping(value = "/insertorderview")
	public String MyPage_order(@RequestParam(value="cartSeq") Integer[] cartSeqArr, Principal principal) {
		
		Orders order = new Orders();
		
		Member member = new Member();
		member.setMemberId(principal.getName());
		order.setMember(member);
		
		int orderSeq = orderService.insertOrder(order);
		
		//구매이력테이블에 상품정보저장
		for(int i = 0; i < cartSeqArr.length; i++) {
			
			insertOrderDetail(orderSeq, cartSeqArr[i]);
			
		}
		return "/member/MyPage_order.html";
	}
	
	//오더디테일에 주문/카트정보저장
	public void insertOrderDetail(int orderSeq, int cartSeq) {
		

		Cart cart = cartService.findCartByCartSeq(cartSeq);
				
		OrdersDetail orderdetail = new OrdersDetail();
		Product product = new Product();
		
		product.setPSeq(cart.getProduct().getPSeq());
		
		//pseq, orderseq, quatity
		
		Orders order = new Orders();
		order.setOrderSeq(orderSeq);
		
		
		orderdetail.setProduct(product);
		orderdetail.setOrder(order);
		orderdetail.setQuantity(cart.getCartStrock());
		
		odService.insertOrderDetail(orderdetail);
		
		
	}
	/*
	//주문리스트불러오기
	public String getOrderDetailList() {
		
		List<Orders> orderList = orderService.findOrderByMemberId(getOrderDetailList());
		
		fo(Orders item : )
		
		return "member/MyPage_order";
	}
	*/
	
}
