package com.ezen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberControlloer {
	
	//마이페이지이동
	@RequestMapping(value = "/MyPage")
	public String Mypage() {
		return "member/MyPage.html";
	}
	
	//회원탈퇴페이지이동
	@RequestMapping(value = "/MyPage_Withdrawal")
	public String Mypage_Withdrawal() {
		return "member/MyPage_Withdrawal.html";
	}
	
	//예약현황페이지이동
	@RequestMapping(value = "/MyPage_Reservation")
	public String Mypage_Reservation() {
		return "member/MyPage_Reservation.html";
	}
	
	//구매이력페이지이동
	@RequestMapping(value = "/MyPage_OrderDetails")
	public String Mypage_OrderDetails() {
		return "member/MyPage_OrderDetails.html";
	}
	
	
	//주문페이지이동
	@RequestMapping(value = "/MyPage_order")
	public String Mypage_order() {
		return "member/MyPage_order.html";
	}
	
	//게시물목록페이지이동
	@RequestMapping(value = "/MyPage_getboardlist")
	public String Mypage_getboardlist() {
		return "member/MyPage_getboardlist.html";
	}
	
	
}
