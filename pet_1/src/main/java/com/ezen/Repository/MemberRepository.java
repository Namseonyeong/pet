package com.ezen.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ezen.entity.Member;
import com.ezen.entity.Product;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	
	
	Optional<Member> findByMemberId(String userMemberId);
	
	Optional<Member> findByMemberEmail(String userMemberEmail);
	
	// 회원 목록 조회
//	@Query(value = "select member_type, member_id, member_name, member_address, member_email from member  where member_type in ('펫시터', '훈련사', '일반')", nativeQuery = true)
	Page<Member> findBymemberId(String searchKeyword, Pageable pageable);
	
	// 멤버 타입별로 리스트 불러오기
//	@Query("SELECT m FROM Member m WHERE m.memberType = ?1")
//	List<Member> findByType(@Param("member_type") char memberType);
	
	// 타입별 test
//	@Query("SELECT m FROM Member m WHERE m.memberType = ?1")
//	Page<Member> findByMemberId(@Param("member_type")char memberType, String searchKeyword, Pageable pageable);
//	
//	
//	@Query("SELECT m FROM Member m WHERE m.memberType = ?1")
//	Page<Member> findByType(@Param("member_type")char memberType, String searchKeyword, Pageable pageable);
	
//	@Query("SELECT m FROM Member m WHERE m.memberType = ?1")
//	public List<Member> findByMemberType(String memberType);

//
//	// 승인목록 test (커피)
//	   @Query("SELECT m FROM Member m WHERE m.memberType = ?1")
//	   public List<Member> findByMemberType(char memberType);
	
	// 타입별 조회 (사용자 목록 test중)
	Page<Member> findBymemberType(String searchKeyword, Pageable pageable);

	





}