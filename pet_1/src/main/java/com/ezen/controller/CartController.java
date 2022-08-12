package com.ezen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ezen.CartService.CartService;
import com.ezen.entity.Cart;
import com.ezen.entity.Member;

@SessionAttributes("member")
@Controller
public class CartController {
	
	@Autowired
	private CartService cartservice;
	
	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}
	
	@GetMapping("/insertCart")
	public String insertCartView(@ModelAttribute("member") Member member) {
		
		return "insertCart";
	}
	
	@PostMapping("/insertCart")
	public String insertCart(@ModelAttribute("member") Member member, Cart cart) {
		
		cartservice.insertCart(cart);
		
		return "redirect:getCartList";
	}
	
	
	@RequestMapping("/getCartList")
	public String getCartList(@ModelAttribute("member") Member member,
							   Model model, Cart cart) {
		
		List<Cart> cartList = cartservice.getCartList(cart);
		
		model.addAttribute("cartList", cartList);
		
		return "getCartList";
	}
	
	@GetMapping("/deleteCart")
	public String deleteCart(@ModelAttribute("member") Member member, Cart cart) {
		
		cartservice.deleteCart(cart);
		
		return "redirect:getCartList";
	}
}
