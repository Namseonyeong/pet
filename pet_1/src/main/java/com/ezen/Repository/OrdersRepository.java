package com.ezen.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ezen.entity.Orders;
import com.ezen.entity.OrdersDetail;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>{

// admin 품목별 매출현황 (날짜조회) SY
//    List<Orders> findAllByRegDateBetween(LocalDate start, LocalDate end);
    Page<Orders> findAllByOrderDateBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
    
  //주문리스트 불러오기
  	@Query("SELECT o FROM Orders o WHERE o.member.memberId=?1 ORDER BY o.orderSeq")
  	List<Orders> findOrderByMemberId(String memberId);
    
//  	Page<OrdersDetail> findByOdSeq(String searchKeyword, Pageable pageable);
}
