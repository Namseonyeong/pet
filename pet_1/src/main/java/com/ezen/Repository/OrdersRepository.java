package com.ezen.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>{

// admin 품목별 매출현황 (날짜조회) SY
//    List<Orders> findAllByRegDateBetween(LocalDate start, LocalDate end);
    List<Orders> findAllByOrderDateBetween(LocalDateTime start, LocalDateTime end);
    
}
