package com.ezen.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	
	Optional<Member> findByMemberId(String userMemberId);
	
	Optional<Member> findByMemberEmail(String userMemberEmail);
	
	// 전체 회원목록 조회_sy NOT IN(여러개의 조건일때 사용)_sy
	@Query("select m FROM Member m WHERE m.memberType != ('관리자')")
	Page<Member> findByMemberType1(Pageable pageable);
	
	// 전체 회원목록 조회 list_sy
	@Query("select m FROM Member m WHERE m.memberType != ('관리자') AND memberType = (:value)")
	Page<Member> findByMemberType1(@Param("value") String searchKeyword, Pageable pageable);

	// 펫시터/훈련사 조회_sy
	@Query("select m From Member m WHERE m.memberType in ('펫시터','훈련사') AND memberStatus =('N') ")
	Page<Member> findByMemberType(Pageable pageable);
	
	// 펫시터/훈련사 프로필 승인_sy
	@Modifying
	@Transactional
	@Query(value="UPDATE member SET member_status='Y' WHERE member_id=?1", nativeQuery=true)
	void updateMemberStatus(String memberId);
	
	// 이메일, 이름으로 id찾기_sy
	Optional<Member> findByMemberEmailAndMemberName(String memberEmail, String memberName);
	
	// 이메일, 이름, 아이디로 Pw변경_sy
//	@Query(value="UPDATE member SET member_pw='1111' WHERE member_id=?1", nativeQuery=true)
	Optional<Member> findByMemberEmailAndMemberNameAndMemberId(String memberEmail, String memberName, String memberId);
	
//	@Modifying
//	@Transactional
	@Query(value="UPDATE member SET member_pw='1111' WHERE member_id=?1", nativeQuery=true)
	void updateMemberPw(String memberId);
	
	//중복확인 id
	boolean existsByMemberId(String memberId);
	
	boolean existsByMemberEmail(String memberEmail);


	


}