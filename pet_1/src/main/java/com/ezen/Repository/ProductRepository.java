package com.ezen.Repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.entity.Product;

//, JpaSpecificationExecutor<Product> 
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	

	// 상품 조회
	Page<Product> findBypKind(String searchKeyword, Pageable pageable);


}
