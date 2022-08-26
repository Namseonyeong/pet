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
	@RequestMapping(value = "/MyPage_cart.html")
	public String MyPage_cart() {
		
		return "member/MyPage_cart.html";
	}
	
	//카트에 상품담기
	@GetMapping("/insertCart")
	public String insertCart(@RequestParam("pseq") Integer pseq, Member member, Principal principal, Integer cartStrock)  {
		
		Cart cart = new Cart();
		
		Product product = new Product();
		product.setPSeq(pseq);
		
		System.out.println("pseq = " + pseq);
		cart.setProduct(product);
		cart.setCartStrock(cart.getCartStrock());
		
		
		member.setMemberId(principal.getName());
		cart.setMember(member);

		
		cartService.insertCart(cart);
		
		return "redirect:/getCartList";
	}

	@RequestMapping("/getCartList")
	public String getCartList(Principal principal, Model model, Cart cart) {
		
		System.out.println("MemberId = " + principal.getName());
		
		List<Cart> cartList = cartService.findCartByMemberId(principal.getName());
		
		for(Cart item : cartList) {
			System.out.println("cart.getProduct = " + cart.getProduct());
		}

		model.addAttribute("cartList", cartList);
		
		return "member/MyPage_cart";
	}

	@GetMapping("/deleteCart")
	public String deleteCart(@ModelAttribute("member") Member member, Integer cartSeq) {
		
		cartService.deleteCart(cartSeq);
		
		return "redirect:getCartList";
	}
}
