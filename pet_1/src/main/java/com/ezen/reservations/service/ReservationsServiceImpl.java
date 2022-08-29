package com.ezen.reservations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.Repository.ReservationsRepository;
import com.ezen.entity.Reservations;

@Service
public class ReservationsServiceImpl implements ReservationsService {

	@Autowired
	private ReservationsRepository rsRepo;

	@Override
	public List<Reservations> getReservationsList(Reservations rs) {
		
		return (List<Reservations>rsRepo.findAll());
	}

	@Override
	public Reservations getReservations(Reservations rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertReservations(Reservations rs) {
		rsRepo.save(rs);
		
	}

	@Override
	public void updateReservations(Reservations rs) {
		Reservations findReservations = rsRepo.findById(rs.getRsSeq()).get();
		
		findReservations.setRsDate(rs.getRsDate());
		findReservations.setRsTime(rs.getRsTime());
		findReservations.setRsEdu(rs.getRsEdu());
		findReservations.setRsTime2(rs.getRsTime2());
		findReservations.setRsKind(rs.getRsKind());
		findReservations.setRsArea(rs.getRsArea());
		
	}

	@Override
	public void deleteReservations(Reservations rs) {
		rsRepo.deleteById(rs.getRsSeq());
		
	}
	
	
}
