package com.ezen.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.ezen.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	//Page<Product> findproductListBy();
	
	// 상품조회 
	//Page<Product> findByTitleContaining(String searchKeyword, Pageable pageable);
	
//	List<Product> productList(Integer p_seq, Pageable pageable); 
	
	
	
}


