// 채완
package com.ezen.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ezen.entity.Member;
import com.ezen.entity.Reservations;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, Integer> {
// 이 레파지토리를 통해서 어떤 엔티티 클래스를 다룰것이냐, 그 해당 엔티티 클래스의 pk(Long 등) 타입은 무엇이냐

	// ----- 멤버Id를 조건으로 예약목록 조회. -----
	@Query("SELECT r FROM Reservations r WHERE r.member.memberId=?1 ORDER BY r.rsSeq")
	Page<Reservations> findReservationsByMemberId(String memberId, Pageable pageable);
	
	// ----- 멤버타입별로 리스트 불러오기 -----
	@Query("SELECT m FROM Member m WHERE m.memberType = ?1")
	public List<Member> findByMemberType(String memberType);

}