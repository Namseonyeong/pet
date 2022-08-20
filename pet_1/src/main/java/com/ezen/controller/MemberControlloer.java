package com.ezen.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.entity.Member;
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
	public String mypage(String memberId, Model model, Principal principal) {
		  Member member = memberService.Memberupdate(principal.getName());
	      model.addAttribute("member", member);
		
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
	


	
	
	// 비밀번호, 핸드폰번호, 주소, 프로필제목, 자기소개 변경
//		public void modify(String memberPw, String memberPhone, String memberAddress, String memberTitle, String memberIy)
	
	}

	
