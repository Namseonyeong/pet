package com.ezen.controller;
// 선영
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.main.service.MemberService;

import lombok.RequiredArgsConstructor;
 
@Controller
@RequiredArgsConstructor  //생성자 주입
@RequestMapping("/member")
public class MemberControlloer {
	
	//private final MemberService memberService;

//	// 회원가입 페이지  /save-form  ,  밑은 save 
//	@GetMapping("/userJoin")
//	public String userJoinForm() {
//		
//		return "member/userJoin";
//	}
//	
//	// 로그인 폼 
//	@GetMapping("/login")
//	public String loginForm() {
//		
//		return "member/login";
//	}
//	
//	// 로그인 페이지 
//	@PostMapping("/save")
//	public String save(@ModelAttribute MemberDTO memberDTO) {
//		
//		memberService.save(memberDTO);
//		
//		return "member/login";
//	}
//	
//	// 로그인 받기 (성공/실패)
//	@PostMapping("/login")
//	public String login(@ModelAttribute MemberDTO memberDTO) {
//		
//		boolean loginResult = memberService.login(memberDTO);
//			if (loginResult) {
//				return "member/main";
//			} else {
//				return "member/login";
//			}
//		
//		}

}


//	//마이페이지이동
//	@RequestMapping(value = "/MyPage")
//	public String Mypage() {
//		return "member/MyPage.html";
//	}
//	
//	//회원탈퇴페이지이동
//	@RequestMapping(value = "/MyPage_Withdrawal")
//	public String Mypage_Withdrawal() {
//		return "member/MyPage_Withdrawal.html";
//	}
//	
//	//예약현황페이지이동
//	@RequestMapping(value = "/MyPage_Reservation")
//	public String Mypage_Reservation() {
//		return "member/MyPage_Reservation.html";
//	}
//	
//	//구매이력페이지이동
//	@RequestMapping(value = "/MyPage_OrderDetails")
//	public String Mypage_OrderDetails() {
//		return "member/MyPage_OrderDetails.html";
//	}
//	
//	
//	//주문페이지이동
//	@RequestMapping(value = "/MyPage_order")
//	public String Mypage_order() {
//		return "member/MyPage_order.html";
//	}
//	
//	//게시물목록페이지이동
//	@RequestMapping(value = "/MyPage_getboardlist")
//	public String Mypage_getboardlist() {
//		return "member/MyPage_getboardlist.html";
//	}
//	
	
