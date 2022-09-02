
package com.ezen.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.entity.Product;


public interface ProductService {
	
	// 상품 등록_sy
	void insertwrite(Product product, MultipartFile file) throws Exception;
	
	// 특정 게시글 불러오기_sy
	Product productView(Integer pSeq);
	
	// 상품목록 삭제_sy
	void productDelete(Integer pSeq);
	
	// 상품 리스트 및 페이징_sy
	Page<Product> productList(Pageable pageable);
	
	// 상품 조회_sy
	Page<Product> productSerchList(String searchKeyword, Pageable pageable);
	
	//상품종류별 조회 (병선)
	Page<Product> productfindByPKind(String Kind, Pageable pageable);
	
	// 매출현황 (test)
//	public List<Product> productByPKind(String Kind);
	
}
