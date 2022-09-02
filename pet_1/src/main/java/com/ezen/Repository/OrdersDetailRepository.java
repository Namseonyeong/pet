package com.ezen.Repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ezen.entity.OrdersDetail;

public interface OrdersDetailRepository extends JpaRepository<OrdersDetail, Integer>{
	
	Page<OrdersDetail> findAllByOdDateBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
	
}

	
