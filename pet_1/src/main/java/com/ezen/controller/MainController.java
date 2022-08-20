package com.ezen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.entity.Member;
import com.ezen.member.service.MemberService;
import com.ezen.security.test.Role;
import com.ezen.security.test.SecurityUserService;

@Controller
public class MainController {
	  	//생성자 주입
		@Autowired
		private MemberService memberService;
		
		@Autowired
		private SecurityUserService securityUserService;
		
		// 메인 페이지
		@GetMapping("/")
		public String index() {
		return "/index";
		}
		
		// 로그인 페이지
		@GetMapping("/Login")
		public String login() {
			return "/main/Login";
		}

		// 회원가입 페이지
		@GetMapping("/UserJoin")
		public String dispSignup() {
		return "/main/UserJoin";
		}

		// 회원가입 처리
		//엔티티 방식
		@PostMapping("/user/UserJoin")
		public String execSignup(Member member, MultipartFile file) throws Exception {
			// admin 으로 회원가입하면 관리자권한을 준다
			if (member.getMemberId().equals("admin")) {
				member.setRole(Role.ROLE_ADMIN); 
			} else {
				member.setRole(Role.ROLE_MEMBER);
			}
			
			// 이미지 공백여부 체크
			if (file.getOriginalFilename().trim().isEmpty()) {
				securityUserService.joinUser(member);
			} else {
				memberService.insertjoin(member, file);
			}
			//member.setRole(Role.ROLE_MEMBER); // 회원가입시 member 기본권한 부여
			//memberService.insertjoin(member, file); // 회원가입시 이미지 저장 서비스 로직
			return "redirect:/Login";
		}
		
		// 로그인 결과 페이지
		@GetMapping("/login/result")
		public String loginResult() {
		return "/index";
		}

		//로그아웃 
		@RequestMapping("/logout")
		public String logoutAction() {
		return "main/logout";
		}
		
		// 로그아웃 결과페이지
		@GetMapping("/logout/result")
		public String logoutResult() {
		return "/index";
		}
		
//		// 로그아웃 결과 페이지
//		@GetMapping("/logout/result")
//		public String dispLogout() {
//		return "main/logout";
//		}
//		
//		//로그아웃 
//		@PostMapping("/logoutAction")
//		public String logoutAction() {
//		return "main/logout";
//		}

		// 접근 거부 페이지
		@GetMapping("/denied")
		public String dispDenied() {
		return "main/denied";
		}

		// 어드민 페이지
		@GetMapping("/admin")
		public String dispAdmin() {
		return "admin/admin";
		}
	}




//		//마이페이지이동
//		@RequestMapping(value = "/MyPage")
//		public String Mypage() {
//			return "member/MyPage.html";
//		}
	//	
//		//회원탈퇴페이지이동
//		@RequestMapping(value = "/MyPage_Withdrawal")
//		public String Mypage_Withdrawal() {
//			return "member/MyPage_Withdrawal.html";
//		}
	//	
//		//예약현황페이지이동
//		@RequestMapping(value = "/MyPage_Reservation")
//		public String Mypage_Reservation() {
//			return "member/MyPage_Reservation.html";
//		}
	//	
//		//구매이력페이지이동
//		@RequestMapping(value = "/MyPage_OrderDetails")
//		public String Mypage_OrderDetails() {
//			return "member/MyPage_OrderDetails.html";
//		}
	//	
	//	
//		//주문페이지이동
//		@RequestMapping(value = "/MyPage_order")
//		public String Mypage_order() {
//			return "member/MyPage_order.html";
//		}
	//	
//		//게시물목록페이지이동
//		@RequestMapping(value = "/MyPage_getboardlist")
//		public String Mypage_getboardlist() {
//			return "member/MyPage_getboardlist.html";
//		}
