package com.ezen.product.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.domain.Product;


public interface ProductService {
	
	// 상품 등록
	void insertwrite(Product product, MultipartFile file) throws Exception;
	
	// 특정 게시글 불러오기
	Product productView(Integer p_seq);
	
	// 상품목록 삭제
	void productDelete(Integer p_seq);
	
	public List<Product> productList();

//	public Page<Product> getproductList(Pageable pageable);
	
}
