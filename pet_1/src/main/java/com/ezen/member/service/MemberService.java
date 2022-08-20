package com.ezen.member.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.entity.Member;

@Service
public interface MemberService {

	
	public void insertjoin(Member member, MultipartFile file) throws Exception;
		
	
}
