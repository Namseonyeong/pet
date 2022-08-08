package com.ezen.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	//Page<Product> findproductListBy();
	
	// 상품조회 
	//Page<Product> findByTitleConte(String searchKeyword);
	
}


