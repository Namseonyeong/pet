package com.ezen.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.CartService.CartService;
import com.ezen.OrderService.OrderService;
import com.ezen.Repository.CartRepository;
import com.ezen.Repository.MemberRepository;
import com.ezen.Repository.OrderDetailInterfaceRepository;
import com.ezen.Repository.ProductRepository;
import com.ezen.entity.Cart;
import com.ezen.entity.Member;
import com.ezen.entity.OrderDetailInterface;
import com.ezen.entity.Orders;
import com.ezen.entity.OrdersDetail;
import com.ezen.entity.Product;
import com.ezen.ordersdetail.service.OrdersDetailService;
import com.ezen.product.service.ProductService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderDetailInterfaceRepository odInRepo;
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private CartRepository cartRepo;
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
	public String MyPage_order(@RequestParam(value="cartSeq") int[] cartSeqArr, Principal principal, Model model) {
		Orders order = new Orders();
		
		Member member = new Member();
		member.setMemberId(principal.getName());
		order.setMember(member);
		
		int orderSeq = orderService.insertOrder(order);
		
		//구매이력테이블에 상품정보저장
		for(int i = 0; i < cartSeqArr.length; i++) {
			
			insertOrderDetail(orderSeq, cartSeqArr[i], member, principal);
			
			//Cart cart = cartService.findCartByCartSeq(cartSeqArr[i]);
			
			cartService.updateCartResult(cartSeqArr[i]);
			
		}

		List<OrderDetailInterface> orderList = odService.findOrderlist(orderSeq);
		model.addAttribute("orderList", orderList);
		model.addAttribute("totalPrice", orderList.get(0).getTotal_price());
		model.addAttribute("odSeq", orderList.get(0).getOrder_seq());

//		return "redirect:/getOrderList";
		return "member/MyPage_order";
	}
	
	/*
	//MyPage_order페이지 이동
	@RequestMapping(value = "/MyPage_order")
	public String MyPage_order() {
		
		return "member/MyPage_order.html";
	}
	*/
	/*
	//카트에서 가져온 상품 주문테이블에담기
	@GetMapping("/insertOrder")
	public String insertOrder(@RequestParam("orderSeq") Integer orderSeq,
							@RequestParam("pSeq") Integer pSeq, Member member,
							@RequestParam("cartSeq") Integer cartSeq,
			Principal principal) {
		
		
		
		
		List<Cart> cartList = cartRepo.findCartByMemberId(principal.getName());
		
		for(Cart item : cartList) {
			Orders order = new Orders();
			
			order.setOrderSeq(orderSeq);
			member.setMemberId(principal.getName());
			order.setMember(member);
			
			orderService.insertOrder(order);
		}

		return "redirect:/getOrderList";
	}
	*/
	
	@GetMapping("/getOrderList")
	public String getOrderList(Principal principal, Model model, Orders order) {
		
		System.out.println("MemberId = " + principal.getName());
		
		List<Orders> orderList = orderService.findOrderByMemberId(principal.getName());
		/*
		for(Orders vo : orderList) {
			System.out.println("---vo---  " + vo);
		}
		*/
		
		model.addAttribute("orderList", orderList);
		
		return "member/MyPage_order";
	}
	
	
	
	
	/*
	
	@GetMapping(value = "/getOrderList")
	public String getOrderList(Principal principal, Model model, Orders order) {
		
		List<Orders> orderList = orderService.findOrderByMemberId(principal.getName());
		
		for(Orders item : orderList) {
			
		}
		
		model.addAttribute("orderList", orderList);
		
		return "member/MyPage_order.html";
	}
	*/
	
	
	
	
	
	
	
	//오더디테일에 주문/카트정보저장
	@PostMapping("/insertOrder")
	public String insertOrderDetail(int orderSeq, int cartSeq, Member member, Principal principal) {
		

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
		orderdetail.setResult('0');
		
		
		odService.insertOrderDetail(orderdetail);
		
		return "redirect:/getOrderDetailList";
	}
	
	
	/*
	//주문리스트불러오기
	@GetMapping("/getOrderDetailList")
	public String getOrderDetailList(Principal principal, Model model, Orders order, int orderSeq) {
		
		System.out.println("MemberId = " + principal.getName());
		
		List<OrderDetailVO> orderList = odService.findOrderlist(orderSeq);
				//orderService.findOrderByMemberId(principal.getName());
		
		for(OrderDetailVO item : orderList) {
			//System.out.println("order.getOrderDetail = " + item.getOrdersDetail());

		}
		
		model.addAttribute("orderList", orderList);
		
		return "member/MyPage_order";
	}
	
	*/
	
	/*
	//오더디테일에 주문/카트정보저장
	@GetMapping("/insertorder")
	public String insertOrder(Orders order, Principal principal) {

		//Cart cart = cartRepo.findCartByCartSeq(cartSeq);

		 Member member = memberRepo.findByMemberId(principal.getName()).get();
	      order.setMember(member);
	      orderService.insertOrder(order);
	      


		return "redirect:/getOrderDetailList";
	}
	*/
	/*
	//결제시업데이트
	@GetMapping("/payment")
	public String payment(@RequestParam(value="orderseq") int[] valueArr) {
		for(int i = 0; i < valueArr.length; i++) {
			
			odInRepo.updateOrderResult(valueArr[i]);
		}
		return "redirect:/insertInfo";
		
	}
	*/
	// 결제시업데이트
	@GetMapping("/payment/{odSeq}")
	public String payment(@PathVariable("odSeq") Integer odSeq, Principal principal, Model model) {
		odInRepo.updateOrderResult(odSeq);

		return "redirect:/MyPage_OrderDetails";
	}

	// 구매이력
	@GetMapping("/MyPage_OrderDetails")
	public String MyPageorderDetail(Principal principal, Model model) {
		List<Orders> orderList = orderService.findOrderByMemberId(principal.getName());
		model.addAttribute("orderList", orderList);

		return "/member/MyPage_OrderDetails";
	}
	
	//주문페이지에서 정보입력란에 입력한 정보
	@GetMapping("/insertInfo")
	public String insertInfo(@RequestParam("orderRce") String orderRce,
							@RequestParam("orderTel") String orderTel,
							@RequestParam("orderAddr1") String orderAddr1, Member member, Principal principal) {
		//이름 번호 주소
		
		Orders order = new Orders();
		
		order.setOrderRce(order.getOrderRce());
		order.setOrderTel(order.getOrderTel());
		order.setOrderAddr1(order.getOrderAddr1());
		
		member.setMemberId(principal.getName());
		order.setMember(member);
		orderService.insertInfo(order);
		
		return "board/Menu.html";
			
	}
	/*
	//주문목록 삭제하기
	@GetMapping("/payment")
	public void deleteOrder(Integer orderSeq) {
		
		
	}
	*/
	
}