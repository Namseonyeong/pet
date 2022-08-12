package com.ezen.member.service;


import org.springframework.web.multipart.MultipartFile;

import com.ezen.entity.Member;


public interface MemberService {
	
	// 회원가입
	void insertjoin(Member member, MultipartFile file)  throws Exception;
	
}
