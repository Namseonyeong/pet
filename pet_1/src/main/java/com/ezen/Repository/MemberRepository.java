package com.ezen.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	
	
	Optional<Member> findByMemberId(String userMemberId);
	
	
	
// 이 레파지토리를 통해서 어떤 엔티티 클래스를 다룰것이냐, 그 해당 엔티티 클래스의 pk(Long 등) 타입은 무엇이냐

	// JPA 문법에 따라 findBy 를 적고 Username을 적었는데 이것은 "select * from user where username = 값"
//	public Member findByMemberId(String memberId);
	
//	Optional<Member> findBymemberId(String memberId);
	
	

}