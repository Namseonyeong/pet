
package com.ezen.product.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.entity.Member;
import com.ezen.entity.Product;


public interface ProductService {
	
	// 상품 등록
	void insertwrite(Product product, MultipartFile file) throws Exception;
	
	// 특정 게시글 불러오기
	Product productView(Integer pSeq);
	
	// 상품목록 삭제
	void productDelete(Integer pSeq);
	
	// 상품 리스트 및 페이징
	Page<Product> productList(Pageable pageable);
	
	// 상품 조회
	Page<Product> productSerchList(String searchKeyword, Pageable pageable);
	
	//상품종류별 조회 (병선)
	Page<Product> productfindByPKind(String Kind, Pageable pageable);
	
	//선영테스트
	// 회원 전체목록 조회
	Page<Member> userManagementList(Pageable pageable);
	// 회원 목록 조회(조건) 페이징
	Page<Member> userManagemenSerchList(String searchKeyword, Pageable pageable);

	// 승인여부 (펫시터, 트레이너)
	Page<Member> userApprovaList(Pageable pageable);
	// 승인여부 (펫시터, 트레이너) 페이징
	Page<Member> userApprovaSerchList(String searchKeyword, Pageable pageable);

	
//	public Page<Member> userApprovaSerchList(@Param("member_type")char memberType, String searchKeyword, Pageable pageable);
	// 타입별로 펫시터, 트레이너 조회
//	List<Member> findBymemberType(String memberType);
	

//	List<Member> findBymemberType(char memberType, Pageable pageable);
	
//	public List<Member> findBymemberType(String memberType);
	
//	// 승인목록 test
//	List<Member> findBymemberType(char memberType);
	
	// 매출현황 (test)
//	public List<Product> productByPKind(String Kind);
	
}
