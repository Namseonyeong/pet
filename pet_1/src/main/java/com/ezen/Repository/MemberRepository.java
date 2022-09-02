package com.ezen.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ezen.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	
	Optional<Member> findByMemberId(String userMemberId);
	
	Optional<Member> findByMemberEmail(String userMemberEmail);
	
	// 회원가입 중복체크 test
//	String existsBymemberId(String memberId);
//	boolean existsBymemberId(String memberId);
//	boolean existsBymemberEmail(String memberEmail);
	
	// 전체 회원목록 조회_sy NOT IN(여러개의 조건일때 사용)
	@Query("select m FROM Member m WHERE m.memberType != ('관리자')")
	Page<Member> findByMemberType1(Pageable pageable);
	
	// 전체 회원목록 조회 list_sy
	@Query("select m FROM Member m WHERE m.memberType != ('관리자') AND memberType = (:value)")
	Page<Member> findByMemberType1(@Param("value") String searchKeyword, Pageable pageable);

	// 펫시터,훈련사 조회_sy
	@Query("select m From Member m WHERE m.memberType in ('펫시터','훈련사')")
	Page<Member> findByMemberType(Pageable pageable);

}