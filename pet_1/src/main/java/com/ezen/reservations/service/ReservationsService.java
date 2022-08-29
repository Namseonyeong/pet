// 채완
package com.ezen.reservations.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ezen.entity.Reservations;

public interface ReservationsService {

	
	// ----- 예약 처리 -----
	void insertReservations(Reservations reservations);

	// ----- 예약 취소 -----
	void deleteReservations(Integer rsSeq);
	
	// ----- 예약 현황 조회 -----
	Page<Reservations> findReservationsByMemberId(String MemberId, Pageable pageable);

}
	
