package com.ezen.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.entity.Reservations;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, Integer> {
// 이 레파지토리를 통해서 어떤 엔티티 클래스를 다룰것이냐, 그 해당 엔티티 클래스의 pk(Long 등) 타입은 무엇이냐


	
	

}