package com.ezen.member.service;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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
	
	// 이름, 이메일로 아이디 찾기 (다시 할것 안됨)_sy
	Member searchMember(String memberEmail, Member member);
	
	// 비밀번호 변경_sy
	void updatePassword(Member member, String newPassword);
	
	// 회원탈퇴_sy
	public void memberDelete(String memberId);
	
//	// 이름과 이메일로 아이디 찾기
//	public Member selectIdByNameEmail(Member member, String memberEmail);
//	
//	// 아이디, 이름, 이메일로 비밀번호 찾기
//	public String selectPwdByIdNameEmail(Member member);
	
	// 회원가입시 중복 체크 test
//	public String checkmemberIdDuplicate(String memberId);
//	
//	public Boolean checkEmailDuplicate(String memberEmail);
//	
//	public boolean checkmemberEmailDuplication(String memberEmail);
//	public boolean checkmemberIdDuplication(String memberId);
	
	// 회원 전체목록 조회_sy
	Page<Member> userManagementList(Pageable pageable);
	
	//회원 목록 조회(조건) 페이징_sy
	Page<Member> userManagemenSerchList(String searchKeyword, Pageable pageable);
		
	//승인여부 (펫시터, 트레이너)_sy
	Page<Member> memberTypeList(Pageable pageable);



}
