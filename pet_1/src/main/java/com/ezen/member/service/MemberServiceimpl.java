package com.ezen.member.service;

import java.io.File;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import com.ezen.Repository.MemberRepository;
import com.ezen.entity.Member;
import com.ezen.entity.Product;
import com.ezen.security.SecurityUserService;

@Service
@Transactional
public class MemberServiceimpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private SecurityUserService securityUserService;

	// 상품 등록 처리(이미지)
	@Override
	public void insertjoin(Member member, MultipartFile file) throws Exception {
		
		// 이미지 저장 경로 지정
		String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\join_img_file";

		// 식별자 (랜덤 파일 이름)
		UUID uuid = UUID.randomUUID();

		String fileName = uuid + "_" + file.getOriginalFilename();
		// 이미지 파일 등록
		File saveFile = new File(projectPath, fileName);
		
		file.transferTo(saveFile);

		// DB에 이미지값 저장
		member.setMemberImage(fileName);
		member.setMemberPath("/join_img_file/" + fileName);

		securityUserService.joinUser(member);
		// memberRepository.save(member);

	}
	
	// 회원정보 조회
	@Override
	public Member Memberupdate(String memberId) {
		
		return memberRepository.findByMemberId(memberId).get();
	}


}

// @Transactional
// public String createMember(MemberForm form) {
// Member member = form.toEntity();
// memberRepository.save(member);
// return member.getMemberId();
// }
