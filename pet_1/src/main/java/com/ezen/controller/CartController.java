package com.ezen.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.CartService.CartService;
import com.ezen.Repository.CartRepository;
import com.ezen.Repository.ProductRepository;
import com.ezen.entity.Cart;
import com.ezen.entity.Member;
import com.ezen.entity.Product;
import com.ezen.product.service.ProductService;

@Controller
public class CartController {

	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private ProductService productService;

	@ModelAttribute("member")
	public Member setMember() {
		
		return new Member();
	}
	
	//MyPage_cart페이지 이동
	@RequestMapping(value = "/MyPage_cart")
	public String MyPage_cart() {
		
		return "member/MyPage_cart.html";
	}
	
	//카트에 상품담기
		@GetMapping("/insertCart")
		public String insertCart(@RequestParam("pSeq") Integer pSeq,
								@RequestParam("price2") Integer price2,
								@RequestParam("cartStrock") Integer cartStrock, Member member, Principal principal)  {
			
			Cart cart = new Cart();
			
			Product product = new Product();
			product.setPSeq(pSeq);
			
			
			System.out.println("=====================> " + pSeq);
			
			System.out.println("=====================> " + member);
			
			System.out.println("pseq = " + pSeq);
			cart.setProduct(product);
			cart.setCartStrock(cartStrock);
			cart.setTotalprice(cart.getCartStrock() * price2);
			
			
			member.setMemberId(principal.getName());
			cart.setMember(member);
			cartService.insertCart(cart);
			
			return "redirect:/getCartList";
		}

	@GetMapping("/getCartList")
	public String getCartList(Principal principal, Model model, Cart cart) {
		
		System.out.println("MemberId = " + principal.getName());
		
		List<Cart> cartList = cartService.findCartByMemberId(principal.getName());
		
		for(Cart item : cartList) {
			System.out.println("cart.getProduct = " + item.getProduct());
		}
		

		model.addAttribute("cartList", cartList);
		
		
		return "member/MyPage_cart";
	}



	@PostMapping("/deleteCart")
	public String deleteCart(@RequestParam Integer[] valueArr) {
		for(Integer i = 0; i < valueArr.length; i++) {
			
		cartService.deleteCart(valueArr[i]);
		
		}
		return "redirect:/getCartList";
	}
}
