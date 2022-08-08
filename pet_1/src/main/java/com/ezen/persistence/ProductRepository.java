package com.ezen.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	//Page<Product> findproductListBy();
	
	
}


