package com.ezen.member.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.Repository.MemberRepository;
import com.ezen.Repository.ProductRepository;
import com.ezen.entity.Member;
import com.ezen.entity.Product;

@Service
public class MemberServiceimpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	// 이미지 저장
	@Override
	public void insertjoin(Member member, MultipartFile file) throws Exception {
		
		//이미지 저장 경로 지정
		String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img_file";
		
		// 식별자 (랜덤 파일 이름) 
		UUID uuid = UUID.randomUUID();
		
		String fileName = uuid + "_" + file.getOriginalFilename(); 
		// 이미지 파일 등록 
		File saveFile = new File(projectPath, fileName);
		
		file.transferTo(saveFile);
		
		// DB에 이미지값 저장
		member.setMemberImage(fileName);
		member.setMemberPath("/img_file/" + fileName);
		
		memberRepository.save(member);
		
	}



	
	
	


	
	
}
