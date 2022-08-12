package com.ezen.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.entity.Product;

//, JpaSpecificationExecutor<Product> 
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	//JpaRepository<T, ID> extends PagingAndSortingRepository

	
	

	// SEQ 들고오는거 테스트
	//List<Product> findProductsOrderBypSeqDESC(Integer pSeq);
	

	// 상품조회
	// Page<Product> findByTitleContaining(String searchKeyword, Pageable pageable);

//	List<Product> productList(Integer p_seq, Pageable pageable); 

	
	
	
	//List<Product> findAll(Sort sort);

	//Page<Product> findAll(Sort sort , Pageable pageable);


}
