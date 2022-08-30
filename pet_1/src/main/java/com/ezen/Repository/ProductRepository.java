package com.ezen.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ezen.entity.Product;

//, JpaSpecificationExecutor<Product> 
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	// 상품등록 (상품조회/sy)
	Page<Product> findBypKind(String searchKeyword, Pageable pageable);
	
	// 상품목록 전체 조회(지병)
	@Query("SELECT p FROM Product p ORDER BY p.pSeq")
	Page<Product> findAll(Pageable pageable);

	// 상품종류 조회 WHERE 절로 조건을 줘서 원하는 상품번호 조회(지병)
	@Query("SELECT p FROM Product p WHERE p.pKind=?1 ORDER BY p.pKind")
	Page<Product> productfindByPKind(String Kind, Pageable pageable);
    
}


