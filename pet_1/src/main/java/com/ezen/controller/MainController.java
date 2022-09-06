package com.ezen.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

	// 회원가입 페이지
	@GetMapping("/UserJoin")
	public String dispSignup() {
	return "/main/UserJoin";
	}

	// 접근 거부 페이지
	@GetMapping("/denied")
	public String dispDenied() {
	return "main/denied";
	}

	// 회원가입 처리
	//엔티티 방식
	@PostMapping("/user/UserJoin")
	public String execSignup(Member member, MultipartFile file, MultipartFile file1) throws Exception {
		// admin 으로 회원가입하면 관리자권한을 준다
		if (member.getMemberId().equals("admin")) {
			member.setRole(Role.ROLE_ADMIN); 
			member.setMemberType("관리자");
		} else {
			member.setRole(Role.ROLE_MEMBER);
		}
		
		// 이미지 공백여부 체크
		if (!file.getOriginalFilename().trim().isEmpty()) {
			memberService.saveImage(member, file, file1);
			
		}
		
		securityUserService.joinUser(member);
		
		//member.setRole(Role.ROLE_MEMBER); // 회원가입시 member 기본권한 부여
		//memberService.saveImage(member, file); // 회원가입시 이미지 저장 서비스 로직
		return "redirect:/Login";
	}
		
	@PostMapping("/overlappingId/action")
	@ResponseBody
	public Map<String, String> overlappingId(Model model, @RequestParam("memberId") String memberId) {
		
		System.out.println("들어오십니까 >>>>>>>>>>>>>>>>>>" + memberId );
//		String memberId = memberService.memberIdpwFind(member);
		
		Map<String, String> map = new HashMap<String,String>(); 
		if (memberService.existsByMemberId(memberId)) {
			map.put("msg", "중복된 ID가 존재합니다.");
			map.put("result", "N");
		} else {
			map.put("msg", "사용가능한 ID 입니다.");
			map.put("result", "Y");
		}
	
		return map; //아이디 조회 결과 화면
	}
		
	// 회원가입시 중복 체크 (이메일)
	@PostMapping("/overlappingEmail/action")
	@ResponseBody
	public Map<String, String> overlappingEmail(Model model, @RequestParam("memberEmail") String memberEmail) {
		
		System.out.println("이메일 들어오십니까  >>>>>>>>>>>>>>>>>>" + memberEmail );
//		String memberId = memberService.memberIdpwFind(member);
		
		Map<String, String> map = new HashMap<String,String>(); 
		if (memberService.existsByMemberEmail(memberEmail) == true) {
			map.put("msg", "중복된 Email이 존재합니다.");
			map.put("result", "N");
		} else {
			map.put("msg", "사용가능한 Email 입니다.");
			map.put("result", "Y");
		}
	
		return map; //아이디 조회 결과 화면
	}	
		
	// IDPW 찾기 
	@GetMapping("/IdpwFind")
	public String idpwFind() {
		   
		return "main/IdpwFind";
	}
	
	// ID 찾기 (이름, 이메일) 
	@PostMapping("/findId/action")
	public String idFindAction(Member member, Model model) {
		
		String memberId = memberService.memberIdFind(member);
		if (memberId == "") {
			model.addAttribute("message", "가입되어 있지 않습니다.");
			model.addAttribute("searchUrl", "/IdpwFind");
		} else {
			model.addAttribute("message", "회원님의 ID는" + " ' " + memberId + " '입니다 ");
			model.addAttribute("searchUrl", "/IdpwFind");
		}
	
		return "main/message"; //아이디 조회 결과 화면
	}
	
	// PW 찾기 (이름, 이메일, 아이디) 
	@PostMapping("/findPw/action")
	public String pwFindAction(Member member, Model model) {
		
		System.out.println("member===============>>" + member);
		String memberPw = memberService.memberPwFind(member);
		if (memberPw == "") {
			model.addAttribute("message", "가입되어 있지 않습니다.");
			model.addAttribute("searchUrl", "/IdpwFind");
		} else {
			model.addAttribute("message", "비밀번호가 '1111'로 변경되었습니다. 로그인 후 비밀번호를 변경해주세요.");
			model.addAttribute("searchUrl", "/IdpwFind");
		}

		return "main/message"; //아이디 조회 결과 화면
	}
	
}






