package com.ezen.Repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ezen.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

	//Page<Product> findproductListBy();
	
	// 상품조회 
	//Page<Product> findByTitleContaining(String searchKeyword, Pageable pageable);
	
//	List<Product> productList(Integer p_seq, Pageable pageable); 
	
	List<Product> findAll(Sort sort);

	Page<Product> findAll(Sort sort , Pageable pageable);

	
	
}


