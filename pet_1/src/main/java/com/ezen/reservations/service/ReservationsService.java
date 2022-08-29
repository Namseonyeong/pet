package com.ezen.reservations.service;

import java.util.List;

import com.ezen.entity.Reservations;

public interface ReservationsService {

	List<Reservations> getReservationsList(Reservations rs);
	
	Reservations getReservations(Reservations rs);
	
	void insertReservations(Reservations rs);
	
	void updateReservations(Reservations rs);
	
	void deleteReservations(Reservations rs);
}
