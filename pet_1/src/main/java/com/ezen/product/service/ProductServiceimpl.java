package com.ezen.product.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.domain.Product;
import com.ezen.persistence.ProductRepository;

@Service
public class ProductServiceimpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	// 상품 등록 처리
	@Override
	public void insertwrite(Product product, MultipartFile file) throws Exception {
		
		//이미지 저장 경로 지정
		String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img_file";
		
		// 식별자 (랜덤 파일 이름) 
		UUID uuid = UUID.randomUUID();
		
		String fileName = uuid + "_" + file.getOriginalFilename(); 
		// 이미지 파일 등록 
		File saveFile = new File(projectPath, fileName);
		
		file.transferTo(saveFile);
		
		// DB에 이미지값 저장
		product.setP_image(fileName);
		product.setP_path("/img_file/" + fileName);
		
		productRepository.save(product);
		
	}

//	   public Product getProduct(Product product) {
//		   return productRepository.findById(product.getP_seq()).get();
//	   }
	
	// 상품 목록 test
//	public Page<Product> productList(Pageable pageable) {
//		System.out.println("서비스==>" + pageable);
//		return productRepository.findAll(pageable);
//	}
	
	// 상품 목록
	@Override
	public List<Product> productList() {
	
	return productRepository.findAll();
}
	
	// 페이징 테스트 
//	@Override
//	@Transactional (readOnly = true)
//	public Page<Product> getproductList(Pageable pageable) {
//	
//		return productRepository.findAll(pageable);
//}
	
	
	// 상품 조회 
//	public Page<Product> productSearchList(String searchKeyword. Pageable pageable) {
//		
//		return productRepository.findByTitleContaining(searchKeyword, Pageable);
//	}
//	
	// 특정 게시글 불러오기
	@Override
	public Product productView(Integer p_seq) {
		
		return productRepository.findById(p_seq).get();
	}
	
	// 특정 상품목록 삭제
	@Override
	public void productDelete(Integer p_seq) {
		
		productRepository.deleteById(p_seq);
		
	}
	


	
	
}
