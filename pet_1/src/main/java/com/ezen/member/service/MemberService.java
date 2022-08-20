package com.ezen.member.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.entity.Member;

@Service
public interface MemberService {

	// 회원가입시 이미지
	public void insertjoin(Member member, MultipartFile file) throws Exception;
	
	// 회원정보 불러오기
	public Member Memberupdate(String memberId);

}
