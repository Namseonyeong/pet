package com.ezen.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	
	
	Optional<Member> findByMemberId(String userMemberId);
	

}