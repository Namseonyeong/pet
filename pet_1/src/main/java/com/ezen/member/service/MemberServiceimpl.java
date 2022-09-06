package com.ezen.member.service;

import java.io.File;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.Repository.MemberRepository;
import com.ezen.controller.MainController;
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
	public void saveImage(Member member, MultipartFile file, MultipartFile file1) throws Exception {
		
		// 이미지 저장 경로 지정
		String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\join_img_file";

		// 식별자 (랜덤 파일 이름)
		UUID uuid = UUID.randomUUID();

		String fileName = uuid + "_" + file.getOriginalFilename();
		String fileName1 = uuid + "_" + file1.getOriginalFilename();
		// 이미지 파일 등록
		File saveFile = new File(projectPath, fileName);
		File saveFile1 = new File(projectPath, fileName1);
		
		file.transferTo(saveFile);
		file1.transferTo(saveFile1);

		// DB에 이미지값 저장
		member.setMemberImage(fileName);
		member.setMemberPath("/join_img_file/" + fileName);
		member.setMemberProImage(fileName1);
		member.setMemberProPath("/join_img_file/" + fileName1);

	}
		
	// 회원정보 조회 (정보 수정)
	@Override
	public Member getMember(String memberId) {
		
		return memberRepository.findByMemberId(memberId).get();
	}
	
	// 회원정보 수정
	@Override
	public void updateMember(Member member, MultipartFile file, MultipartFile file1, Principal principal) throws Exception {

		Member memberTemp = getMember(principal.getName());
		System.out.println("memberTemp ==> " + memberTemp);
//		memberTemp.setMemberPw(passwordEncoder.encode(member.getMemberPw()));
		memberTemp.setMemberPw(memberTemp.getMemberPw());
		memberTemp.setMemberPhone(member.getMemberPhone());
		memberTemp.setMemberTitle(member.getMemberTitle());
		memberTemp.setMemberIy(member.getMemberIy());
		memberTemp.setMemberImage(memberTemp.getMemberImage());
		memberTemp.setMemberPath(memberTemp.getMemberPath());
		memberTemp.setMemberProImage(memberTemp.getMemberProImage());
		memberTemp.setMemberProPath(memberTemp.getMemberProPath());
		
		saveImage(memberTemp, file, file1);
		memberRepository.save(memberTemp);
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
	
	// 이름, 메일로 아이디 찾기
	@Override
	public String memberIdFind(Member member) {
		Optional<Member> memberList = memberRepository.findByMemberEmailAndMemberName(member.getMemberEmail(), member.getMemberName());
		
		return memberList.isEmpty() ? "" : memberList.get().getMemberId();
	}
	
	// 이메일, 이름, 아이디로 Pw변경_sy
	@Override
	public String memberPwFind(Member member) {
		Optional<Member> memberList = memberRepository.findByMemberEmailAndMemberNameAndMemberId(member.getMemberEmail(), member.getMemberName(), member.getMemberId());
		
		if (memberList.isEmpty()) {
			return "false"; 
		} else {
			String memberId = member.getMemberId();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String memberPw = encoder.encode("1111");
			memberRepository.updateMemberPw(memberPw, memberId);
			return "true";
		}
	}
	
	// 전체사용자 목록_sy
	@Override
	public Page<Member> userManagementList(Pageable pageable) {
		
		return memberRepository.findByMemberType1(pageable);
	}
	
	// 전체사용자 목록 페이징_sy
	@Override
	public Page<Member> userManagemenSerchList(String searchKeyword, Pageable pageable) {
		
		return memberRepository.findByMemberType1(searchKeyword, pageable);
	}
	
	// 회원승인 여부 (펫시터, 트레이너)_sy
	@Override
	public Page<Member> memberTypeList(Pageable pageable) {
		
		return memberRepository.findByMemberType(pageable);
	}
	
	// 회원 프로필 승인_sy
	@Override
	public void userProApproval(String[] valueArr) {

		for (int i = 0; i < valueArr.length; i++) {
			memberRepository.updateMemberStatus(valueArr[i]);
		}
		
		return;
	}
	
	// 회원가입시 id 중복체크
	@Transactional
	@Override
	public boolean existsByMemberId(String memberId) {
		
		return memberRepository.existsByMemberId(memberId);
	}
	
	@Transactional
	@Override
	public boolean existsByMemberEmail(String memberEmail) {
		
		return memberRepository.existsByMemberEmail(memberEmail);
	}

}

	
	

	
	