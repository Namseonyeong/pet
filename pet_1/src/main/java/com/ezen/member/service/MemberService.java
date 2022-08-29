package com.ezen.member.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.entity.Member;

@Service
public interface MemberService {

	// 회원가입시 이미지
	void insertjoin(Member member, MultipartFile file) throws Exception;
	
	// 회원정보 불러오기
	Member getMember(String memberId);
	
	// 회원정보 수정
	void updateMember(Member member, MultipartFile file, Principal memberDelete) throws Exception;
	// principal.getName()

	// 이름, 이메일로 아이디 찾기 (다시 할것 안됨)
	Member searchMember(String memberEmail, Member member);
	
	// 비밀번호 변경
	void updatePassword(Member member, String newPassword);
	
	// 회원탈퇴
	public void memberDelete(String memberId);
	
	
//	// 이름과 이메일로 아이디 찾기
//	public Member selectIdByNameEmail(Member member, String memberEmail);
//	
//	// 아이디, 이름, 이메일로 비밀번호 찾기
//	public String selectPwdByIdNameEmail(Member member);
//
//	// 회원 ID를 조건으로 비밀번호 변경
//	public void changePassword(Member member) ;


	
	// 멤버타입별로 리스트불러오기 -채완
	List<Member> findByMemberType(String memberType);
	
	// Id로 시터 상세정보 불러오기 -채완
	Optional<Member> getSitter(Member member);
	
	// Id로 훈련사 상세정보 불러오기 -채완
	Optional<Member> getTrainer(Member member);
	
	
}
