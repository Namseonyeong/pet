package com.ezen.member.service;

import java.io.File;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.Repository.MemberRepository;
import com.ezen.entity.Member;
import com.ezen.security.SecurityUserService;

@Service
@Transactional
public class MemberServiceimpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private SecurityUserService securityUserService;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	// 회원가입 (이미지)
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
		member.setMemberProImage(fileName);
		member.setMemberProPath("/join_img_file/" + fileName);

		securityUserService.joinUser(member);
		// memberRepository.save(member);

	}
		
	// 회원정보 조회
	@Override
	public Member getMember(String memberId) {
		
		return memberRepository.findByMemberId(memberId).get();
	}
	
	// 이름, 메일로 아이디 찾기 (test) 
	@Override
	public Member searchMember(String memberEmail, Member member) {
		
		System.out.println( " memberEmail============>" + memberEmail);
		return memberRepository.findByMemberEmail(memberEmail).get();
	}
	

	// 회원정보 수정
	@Override
	public void updateMember(Member member, MultipartFile file, Principal principal) throws Exception {

		System.out.println("들어오니?");
		System.out.println("--========>" + principal.getName().getClass().getName());
		Member memberTemp = getMember(principal.getName());
		System.out.println("memberTemp ==> " + memberTemp);
//		memberTemp.setMemberPw(passwordEncoder.encode(member.getMemberPw()));
//		memberTemp.setMemberPw(member.getMemberPw());
		memberTemp.setMemberPhone(member.getMemberPhone());
		memberTemp.setMemberTitle(member.getMemberTitle());
		memberTemp.setMemberIy(member.getMemberIy());
		memberTemp.setMemberImage(memberTemp.getMemberImage());
		memberTemp.setMemberPath(memberTemp.getMemberPath());
		
		insertjoin(memberTemp, file);
		return;
    
	}

	// 비밀번호 변경
	@Override
	public void updatePassword(Member member, String newPassword) {
		member.setMemberPw(passwordEncoder.encode(newPassword));
		memberRepository.save(member);
	}

	// 탈퇴
	@Override
	public void memberDelete(String memberId) {
		memberRepository.deleteById(memberId);
	}

	
	// 회원가입시 중복 체크 test
//	@Override
//	public String checkmemberIdDuplicate(String memberId) {
//		return memberRepository.existsBymemberId(memberId);
//	}
//	
//	@Override
//	public Boolean checkEmailDuplicate(String memberEmail) {
//		return memberRepository.existsBymemberEmail(memberEmail);
//	}

	@Transactional(readOnly = true)
	@Override
	public boolean checkmemberIdDuplication(String memberId) {
		boolean memberIdDuplicate = memberRepository.existsBymemberId(memberId);
		return memberIdDuplicate;
		
	}

	@Transactional(readOnly = true)
	@Override
	public boolean checkmemberEmailDuplication(String memberEmail) {
		boolean memberEmailDuplicate = memberRepository.existsBymemberEmail(memberEmail);
		return  memberEmailDuplicate;
	}
	

}

	
	

	
	