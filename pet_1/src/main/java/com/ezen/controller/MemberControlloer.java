package com.ezen.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

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
		
		
		// 마이페이지 (회원정보 조회)
		@GetMapping("/MyPage")
		public String mypage(String memberId, Model model, Principal principal) {
			  Member member = memberService.getMember(principal.getName());
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
		
		
		// 회원정보 수정 (추가할것)
		@PostMapping("/Update")
		public String updateMember(Member member, Model model, MultipartFile file, Principal principal) throws Exception {
			System.out.println("member ==>" + member);
		     System.out.println("왜 안들어오ㅏ 개때끼야!!!!!!!!!!!!!!!!!!!!!!!!!!");
		      
		    	  memberService.updateMember(member, file, principal);
		    	  model.addAttribute("message", "개인정보수정이 완료되었습니다.");
		  		  model.addAttribute("searchUrl", "/member/MyPage");
		  
			return "/member/message";
		}

	
	   // 비밀번호 변경 (화면)
	   @GetMapping("/PwChange")
	   public String pwChange(Model model, Principal principal) {
	      Member member = memberService.getMember(principal.getName());
	      model.addAttribute("member", member);
	      return "member/PwChange";
	   }

	
	   // 비밀번호 변경 로직
	   @PostMapping("/updatePassword")
	   public String passwordEdit(Model model, Member member, @RequestParam("newPw") String newPw,
	         @RequestParam("newPwConfirm") String newPwConfirm, Principal principal) {

	      // System.out.println("============= 로그인사용자아이디 >>>>>>" + principal.getName());
	      // System.out.println("============= 현재비밀번호 >>>>>>"member.getMemberPw());
	      // System.out.println("============= 새 비밀번호 >>>>>>" + newPw);
	      // System.out.println("============= 새 비밀번호 확인 >>>>>>" + newPwConfirm);

	      /*
	       * Principal : 인증완료된 후 컨트롤러, 뷰 어디서든 사용가능한 최상위 인터페이스
	       * 단. Principal 해당 객체는 getName() 정도만 사용이 가능하다.
	       * 즉. ID 정보만 가져올 수 있다.
	       */

	      /*
	       * 기존 비밀번호를 가져오기 위해 Principal 객체를 사용하여 ID정보로
	       * getMember 메서드 호출하여 Member 객체 타입으로 받는다.
	       */
	      Member tmpMember = memberService.getMember(principal.getName());
	      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	      /*
	       * BCryptPasswordEncoder의 matches 메서드 사용으로 암호화 되어있는 비밀번호 비교가 가능하다.
	       * 앞 : 현재 비밀번호 / 뒤 : 기존 비밀번호
	       * matches() 메서드 내부 : 현재 비밀번호를 matches 메서드 안에서 암호화하여 기존 비밀번호와 비교하여 결과를 리턴해준다.
	       */
	      if (!encoder.matches(member.getMemberPw(), tmpMember.getMemberPw())) { // [현재 비밀번호] 와 [기존 비밀번호] 다를경우
	         model.addAttribute("message", "기존 패스워드와 일치하지 않습니다.");
	      } else {
	         if (newPw.equals(newPwConfirm)) { // [새 비밀번호] 와 [새 비밀번호 확인] 이 같은 경우
	            memberService.updatePassword(tmpMember, newPw); // 서비스 메서드 호출
	            model.addAttribute("message", "비밀번호 변경이 완료되었습니다.");
	         } else {
	            model.addAttribute("message", "변경할 비밀번호가 일치하지 않습니다.");
	         }
	      }

	      model.addAttribute("searchUrl", "/member/PwChange");

	      return "admin/message";
	   }
	   
	   
		// 회원탈퇴
		@GetMapping("/MyPage_Withdrawal")
		public String MyPageWithdrawal() {
		return "/member/MyPage_Withdrawal";
		}
		
		// 회원탈퇴 (로직)
		@PostMapping("/Deletemember") 
		public String memberWithdrawal(Member member, Model model, Principal principal) {
			System.out.println("안들어오세요??????????????????????????? ");
			
			  Member witMember = memberService.getMember(principal.getName());
		      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		      if (!encoder.matches(member.getMemberPw(), witMember.getMemberPw())) { 
		         model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
		         model.addAttribute("searchUrl", "/member/MyPage_Withdrawal");
		      } else {
		    	  if (encoder.matches(member.getMemberPw(), witMember.getMemberPw())) {
		    	  System.out.println("===========================요기까지 들어오나요");
		    	  	  memberService.memberDelete(witMember.getMemberId());
		    	  	  
		    	  	System.out.println("===========================헤이헤이헤이헤이헤이헤이헤이헤이!!!");
		    		  model.addAttribute("message", "탈퇴가 완료되었습니다.");
		    		  SecurityContextHolder.clearContext();
		    	  } 
		      }
		      
		      return "admin/message";
		   }
		
}
			
			
//			@GetMapping("/MyPage_Withdrawal")
//			public void daleteMember(Member member, Model model, Principal principal) {
//				
//				Member deleteMemberId = memberService.getMember(principal.getName());
//				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//				if (!encoder.matches(member.getMemberPw(), deleteMemberId.getMemberPw())) {
//					model.addAttribute("message", "기존 패스워드와 일치하지 않습니다."); 
//				}
//				MemberRepository.deleteBymemberId(deleteMemberId);
//			}
	   
	   
	
