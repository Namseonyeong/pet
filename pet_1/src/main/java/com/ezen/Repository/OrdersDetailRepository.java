package com.ezen.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ezen.entity.OrdersDetailSy;
import com.ezen.entity.Product;

public interface OrdersDetailRepository extends JpaRepository<OrdersDetailSy, Integer>{

	
	// 매출현황조회 (SY)
//	@Query("SELECT p, o FROM Product p, orders o ORDER BY p.pSeq, o.order_seq")
	Page<OrdersDetailSy> findAll(Pageable pageable);
	
	// 매출현황조회 (SY)
	Page<OrdersDetailSy> findByOdSeq(String searchKeyword, Pageable pageable);
	
	// 매출현황조회test (SY)
	List<OrdersDetailSy> findAllByProduct(Product product);
	
	// 매출현황조회test (SY)
//	@Query(value = "SELECT p FROM Product p ORDER BY p.pKind")
//	@Query("SELECT o FROM OrdersDetail o WHERE o.product.pSeq in (SELECT pSeq FROM product WHERE pKind=?1) ORDER BY o.odSeq")
//	Page<OrdersDetailSy> findByproductPKindQuery(@Param("pKind") String pKind, Pageable pageable);

	// 매출현황조회test (SY)
	Page<OrdersDetailSy> findByProduct_PKind(String searchKeyword, Pageable pageable);
	
	// 매출현황조회test (SY)test
	Page<OrdersDetailSy> findByOdSeq(String startDate, String endDate, String searchKeyword, Pageable pageable);
	
}

	
