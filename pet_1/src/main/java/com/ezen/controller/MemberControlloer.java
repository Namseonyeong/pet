package com.ezen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.member.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
 
@Controller
@AllArgsConstructor
@RequiredArgsConstructor  //생성자 주입
@RequestMapping("/member") 
public class MemberControlloer {
	
	@Autowired
	private MemberService memberService;
	
	// 마이페이지 (개인정보 수정)
	@GetMapping("/MyPage")
	public String mypage() {
	return "/member/MyPage";
	}
	
	// 내가 쓴 글 목록
	@GetMapping("/MyPage_getboardlist")
	public String MyPageboardlist() {
	return "/member/MyPage_getboardlist";
	}
	
	// 장바구니
	@GetMapping("/MyPage_order")
	public String MyPageorder() {
	return "/member/MyPage_order";
	}
	
	}

	
