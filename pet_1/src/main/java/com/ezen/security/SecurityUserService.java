package com.ezen.security;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.Repository.MemberRepository;
import com.ezen.entity.Member;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Component
public class SecurityUserService implements UserDetailsService {

	private MemberRepository memberRepository;

	@Transactional
	// 엔티티 방식
	public Member joinUser(Member member) {
		// 비밀번호 암호화
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setMemberPw(passwordEncoder.encode(member.getMemberPw()));

		return memberRepository.save(member);
	}

	// 로그인시 id 존재여부 확인
	@Override
	public UserDetails loadUserByUsername(String userMemberId) throws UsernameNotFoundException {
		Optional<Member> findMember = memberRepository.findByMemberId(userMemberId);
		if (!findMember.isPresent()) {
			throw new UsernameNotFoundException("존재하지 않는 아이디 입니다.");
		} 

		Member member = findMember.get();
		return new User(member.getMemberId(), member.getMemberPw(),
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
	}


	

	// public UserDetails loadUserByUsername(String userMemberId) throws
	// UsernameNotFoundException {
	//
	// Optional<Member> userEntityWrapper =
	// memberRepository.findByMemberId(userMemberId);
	// Member userEntity = userEntityWrapper.get();
	//
	// List<GrantedAuthority> authorities = new ArrayList<>();

	// if (("admin@example.com").equals(userMemberId)) {
	// authorities.add(new SimpleGrantedAuthority(Role.ROLE_MEMBER.toString()));
	// } else {
	// authorities.add(new SimpleGrantedAuthority(Role.ROLE_ADMIN.toString()));
	// }
	//
	// return new User(userEntity.getMemberId(), userEntity.getMemberPw(),
	// authorities);
	// }

}
