package com.ezen.product.service;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.Repository.MemberRepository;
import com.ezen.Repository.ProductRepository;
import com.ezen.entity.Member;
import com.ezen.entity.Product;

@Service
public class ProductServiceimpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private MemberRepository memberRepository;

	// 상품 등록 처리(이미지)
	@Override
	public void insertwrite(Product product, MultipartFile file) throws Exception {

		// 첨부파일 공백이 아닐경우 이미지 저장
		if (!file.getOriginalFilename().trim().isEmpty()) {
			// 이미지 저장 경로 지정
			String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img_file";

			// 식별자 (랜덤 파일 이름)
			UUID uuid = UUID.randomUUID();

			String fileName = uuid + "_" + file.getOriginalFilename();
			// 이미지 파일 등록
			File saveFile = new File(projectPath, fileName);

			file.transferTo(saveFile);

			// DB에 이미지값 저장
			product.setPImage(fileName);
			product.setPPath("/img_file/" + fileName);
		}

		productRepository.save(product);

	}

	// 상품 목록
	@Override
	public Page<Product> productList(Pageable pageable) {

		return productRepository.findAll(pageable);
	}

	// 상품 조회 (목록)
	@Override
	public Page<Product> productSerchList(String searchKeyword, Pageable pageable) {

		return productRepository.findBypKind(searchKeyword, pageable);
	}

	// 특정 게시글 불러오기
	@Override
	public Product productView(Integer pSeq) {

		return productRepository.findById(pSeq).get();
	}

	// 특정 상품목록 삭제
	@Override
	public void productDelete(Integer pSeq) {

		productRepository.deleteById(pSeq);

	}

	// 회원목록 
	@Override
	public Page<Member> userManagementList(Pageable pageable) {

		return memberRepository.findAll(pageable);
	}

	// 회원목록
	@Override
	public Page<Member> userManagemenSerchList(String searchKeyword, Pageable pageable) {

		return memberRepository.findBymemberId(searchKeyword, pageable);
	}
	

	// 회원승인 여부 (펫시터, 트레이너)
	@Override
	public Page<Member> userApprovaList(Pageable pageable) {

		return memberRepository.findAll(pageable);
	}
	
	// 회원승인 여부 (펫시터, 트레이너) - 페이징
	@Override
	public Page<Member> userApprovaSerchList(String searchKeyword, Pageable pageable){
		return memberRepository.findBymemberId(searchKeyword, pageable);
	}

		//상품종류별 조회(지병)
		@Override
		public Page<Product> productfindByPKind(String Kind, Pageable pageable){
			
			return productRepository.productfindByPKind(Kind, pageable);
		}


		// 테스트
			// 타입별로 리스트 불러오기 (펫시터, 트레이너)
//	@Override
//	public List<Member> findBymemberType(char memberType, Pageable pageable){
//		return memberRepository.findBymemberType(memberType);
//	}	
	
	// 승인여부 타입별 목록
//	@Override
//	public Page<Member> userApprovaList(char memberType, Pageable pageable) {
//
//		return memberRepository.findAll(pageable);
//	}
	
//	// 승인여부 타입별로 (페이징)
//	@Override
//	public Page<Member> userApprovaSerchList(@Param("member_type")char memberType, String searchKeyword, Pageable pageable){
//		
//		return memberRepository.findByType(memberType, searchKeyword, pageable);
//	}
	
	//test
//	@Override
//	public List<Member> findBymemberType(String memberType) {
//		return memberRepository.findByMemberType(memberType);
//	}
	
	
//	// 승인목록 test
//	   @Override
//	   public List<Member> findBymemberType(char memberType) {
//	      
//	      
//	      return memberRepository.findByType(memberType);
//	   }
//	
}
