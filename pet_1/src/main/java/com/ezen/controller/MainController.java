package com.ezen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.entity.Member;
import com.ezen.member.service.MemberService;
import com.ezen.security.Role;
import com.ezen.security.SecurityUserService;

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
				member.setMemberType("관리자");
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
		
		// 회원가입시 중복 체크 test
//		@GetMapping("/UserJoin/{memberId}/esists")
//		public ResponseEntity<String> checkmemberIdDuplicate(@PathVariable String memberId) {
//			System.out.println("======================> 멤버 아이디 " + memberId);
//			return ResponseEntity.ok(memberService.checkmemberIdDuplicate(memberId));
//		}
//		
//		@GetMapping("/UserJoin/{memberEmail}/esists")
//		public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String memberEmail) {
//			return ResponseEntity.ok(memberService.checkEmailDuplicate(memberEmail));
//		}
		
		@GetMapping("/auth/joinProc/{nickname}/exists")
		public ResponseEntity<Boolean> checkmemberIdDuplicate(@PathVariable String memberId){
			return ResponseEntity.ok(memberService.checkmemberIdDuplication(memberId));
		}
		@GetMapping("/auth/joinProc/{email}/exists")
		public ResponseEntity<Boolean> checkmemberEmailDuplicate(@PathVariable String memberEmail){
			return ResponseEntity.ok(memberService.checkmemberEmailDuplication(memberEmail));
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

		// 접근 거부 페이지
		@GetMapping("/denied")
		public String dispDenied() {
		return "main/denied";
		}
		
		// IDPW 찾기 
		@GetMapping("/IdpwFind")
		public String idpwFind(String memberEmail, Model model) {
			
//			Member memberFidnId = memberService.getMember(member.getMemberId());
//			model.addAttribute("member", member);
		return "main/IdpwFind";
		      }
		
//		// IDPW 찾기 (action) 
		@PostMapping("/findId/action")
		public String idpwFindAction(String memberEmail, Model model) {
			System.out.println( " dddddddddddddddddddddddddddddddddddd");
			
//			System.out.println("memberEmail ===> " + member.getMemberEmail());
//			System.out.println("member.getMemberName(); ===> " + member.getMemberName());
//			Member memberFidnId = memberService.getMember(member.getMemberEmail());
//			
//			if (memberFidnId != null) {
//				model.addAttribute("massage", 1);
//				model.addAttribute("memberId", memberFidnId);
//			} else {
//				model.addAttribute("message", "가입되어 있지 않습니다.");
//			}
			
			return "main/findResult"; //아이디 조회 결과 화면
		}
		
		

		

}






