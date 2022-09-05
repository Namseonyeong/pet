// 채완 

package com.ezen.Repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ezen.entity.CustomerService;


@Repository
public interface CustomerServiceRepository extends JpaRepository<CustomerService, Integer> {
// 이 레파지토리를 통해서 어떤 엔티티 클래스를 다룰것이냐, 그 해당 엔티티 클래스의 pk(Long 등) 타입은 무엇이냐

	// ----- 고객센터 게시글 전체 조회. -----
	@Query("SELECT c FROM CustomerService c ORDER BY c.csSeq")
	Page<CustomerService> findAll(Pageable pageable);
	
	// ----- 멤버Id를 조건으로 고객센터 게시글 조회. -----
	@Query("SELECT c FROM CustomerService c WHERE c.member.memberId=?1 ORDER BY c.csSeq")
	Page<CustomerService> findCustomerListByMemberId(String memberId, Pageable pageable);

	// ----- 조회수 증가. -----
	@Modifying
	@Transactional
	@Query(value="UPDATE customer_service  SET cs_cnt=cs_cnt+1 WHERE cs_seq=?1", nativeQuery=true)
	void UpdateCsCnt (Integer csSeq);
	
}