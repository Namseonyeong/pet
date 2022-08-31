// 채완

package com.ezen.reservations.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ezen.entity.Member;
import com.ezen.entity.Reservations;

public interface ReservationsService {

	// ----- 멤버타입별로 리스트불러오기 -----
	List<Member> findByMemberType(String memberType);
	
	// ----- Id로 시터 상세정보 불러오기 -----
	Optional<Member> getSitter(Member member);
	
	// ----- Id로 훈련사 상세정보 불러오기 -----
	Optional<Member> getTrainer(Member member);
	
	// ----- 예약 처리 -----
	void insertReservations(Reservations reservations);

	// ----- 예약 취소 -----
	void deleteReservations(Integer rsSeq);
	
	// ----- 예약 현황 조회 -----
	Page<Reservations> findReservationsByMemberId(String MemberId, Pageable pageable);
	
}
	
