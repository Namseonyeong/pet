// 채완 

package com.ezen.reservations.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ezen.Repository.MemberRepository;
import com.ezen.Repository.ReservationsRepository;
import com.ezen.entity.Member;
import com.ezen.entity.Reservations;

@Service
public class ReservationsServiceImpl implements ReservationsService {

	@Autowired
	private ReservationsRepository rsRepo;
	@Autowired
	private MemberRepository memberRepository;

	
	// ----- 멤버타입별로 리스트불러오기 -----
	@Override
	public List<Member> findByMemberType(String memberType) {
			
		return rsRepo.findByMemberType(memberType);
	}
	
	// ----- Id로 시터 상세정보 불러오기 -----
	@Override
	public Optional<Member> getSitter(Member member) {
		
		return memberRepository.findByMemberId(member.getMemberId());
	}
	
	// ----- Id로 훈련사 상세정보 불러오기 -----
	@Override
	public Optional<Member> getTrainer(Member member) {
		
		return memberRepository.findByMemberId(member.getMemberId());
	}
	
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



