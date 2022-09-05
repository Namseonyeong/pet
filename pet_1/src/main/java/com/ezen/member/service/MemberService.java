package com.ezen.member.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.entity.Member;

@Service
public interface MemberService {

	// 회원가입시 이미지_sy
	public void saveImage(Member member, MultipartFile file, MultipartFile file1) throws Exception;
	
	// 회원정보 불러오기_sy
	Member getMember(String memberId);
	
	// 회원정보 수정_sy
	void updateMember(Member member, MultipartFile file, MultipartFile file1, Principal memberDelete) throws Exception;
	
	// 비밀번호 변경_sy
	void updatePassword(Member member, String newPassword);
	
	// 회원탈퇴_sy
	public void memberDelete(String memberId);
	
	// 이메일, 이름으로 id찾기_sy
	String memberIdFind(Member member);
	
	// 이메일, 이름, 아이디로 Pw변경_sy
	String memberPwFind(Member member);
	
	// 회원 전체목록 조회_sy
	Page<Member> userManagementList(Pageable pageable);
	
	// 회원 목록 조회(조건) 페이징_sy
	Page<Member> userManagemenSerchList(String searchKeyword, Pageable pageable);
		
	// 승인여부 (펫시터, 트레이너)_sy
	Page<Member> memberTypeList(Pageable pageable);
	
	// 회원 프로필 승인 (펫시터, 트레이너)_sy
	void userProApproval(String[] valueArr);
	
	// 회원가입시 id 중복체크 
	public boolean existsByMemberId(String memberId);
	
	public boolean existsByMemberEmail(String memberEmail);

}
