package com.ezen.controller;

// 시큐리티 테스트 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.Repository.MemberRepository;
import com.ezen.entity.Member;

import lombok.RequiredArgsConstructor;

@Controller //View를 리턴하겠다! 
@RequiredArgsConstructor
public class IndexController {
	
	private final MemberRepository memberRepository;
//	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	 @GetMapping({"", "/"})
	    public String index() {
	        return "index.html"; // src/main/resources/templates/index.html
	    }

	    @GetMapping("/Admin")
	    public @ResponseBody String admin() {
	        return "admin/Manager.html";
	    }

	    @GetMapping("/Login")
	    public String LoginForm() {
	        return "member/Login.html";
	    }
}
//
//	    @GetMapping("/UserJoin")
//	    public String joinForm() {
//	        return "member/UserJoin.html";
//	    }
//
//	    @PostMapping("/join")
//	    public String join(Member member) {
//	        System.out.println(member);
//	        member.setRole("ROLE_USER");
//	        String rawMemberPw = member.getMemberPw();
//	        String encMemberPw = bCryptPasswordEncoder.encode(rawMemberPw);
//	        member.setMemberPw(encMemberPw);
//	        memberRepository.save(member);
//	        return "redirect:member/Login.html";
//	    }
//	}
//
//
