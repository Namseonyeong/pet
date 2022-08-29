// 채완
package com.ezen.reservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ezen.Repository.ReservationsRepository;
import com.ezen.entity.Reservations;



@Service
public class ReservationsServiceImpl implements ReservationsService {

	@Autowired
	private ReservationsRepository rsRepo;

	
	// ----- 예약처리 -----
	@Override
	public void insertReservations(Reservations reservations) {
		rsRepo.save(reservations);	
		
	}

	// ----- 예약 취소 -----
	@Override
	public void deleteReservations(Integer rsSeq) {
		
		rsRepo.deleteById(rsSeq);
	}

	// ----- 예약 현황 조회 -----
	@Override
	public Page<Reservations> findReservationsByMemberId(String MemberId, Pageable pageable) {
		
		return (Page<Reservations>)rsRepo.findReservationsByMemberId(MemberId, pageable);
	}
}



