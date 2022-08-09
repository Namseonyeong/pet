package com.ezen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.Repository.ProductRepository;
import com.ezen.entity.Product;
import com.ezen.product.service.ProductService;
import com.ezen.product.service.ProductServiceimpl;

//admin 컨트롤러 (DB)

@Controller
@RequestMapping("/product")
public class AdminController {

	@Autowired
	private ProductService productService;
	
//	// 대시보드로 이동
//	@RequestMapping(value = "/dashboards")
//	public String dashboards() {
//		return "admin/dashboards.html";
//	}
//	
//	// 매니저 메인화면
//	@RequestMapping(value = "/manager")
//	public String manager() {
//		return "admin/manager.html";
//	}
	
	// 매니저 상품 등록
//	@RequestMapping(value = "/productCrystal")
//	public String ProductCrystal() {
//		return "admin/productCrystal.html";
//	}
	
//	// 상품관리 (카테고리별 매출현황)
//	@RequestMapping(value = "/productManagement")
//	public String productManagement() {
//		return "admin/productManagement.html";
//	}
//	
//	// 상품등록 (등록)
//	@RequestMapping(value = "/productRegistration")
//	public String productRegistration() {
//		return "admin/productRegistration.html";
//	}
//	
//	// 매출현황 (카테고리별 매출현황)
//	@RequestMapping(value = "/salesManagement")
//	public String salesManagement() {
//		return "admin/salesManagement.html";
//	}
//	
//	// 펫시터/훈련사 승인대기목록
//	@RequestMapping(value = "/userApproval")
//	public String userApproval() {
//		return "admin/userApproval.html";
//	}
//	
//	// 사용자 관리 (전체 사용자 목록 조회)
//	@RequestMapping(value = "/userManagement")
//	public String userManagement() {
//		return "admin/userManagement.html";
//	}

	
	// 테스트 (상품 등록)
	@PostMapping("/productwrite")
	public String productwrite(Product product, Model model, MultipartFile file) throws Exception  {
		
		productService.insertwrite(product, file);
		System.out.println("번호 : " + product.getP_seq());
//		System.out.println("분류 : " + product.getP_kind());
		System.out.println("이름 : " + product.getP_name()); 
		System.out.println("원가 : " + product.getPrice1());
		System.out.println("판매가 : " + product.getPrice2());
		System.out.println("순수익 : " + product.getPrice3());
		System.out.println("상세정보 : " + product.getP_content());
//		System.out.println("이미지 : " + product.getP_image());
//		
		model.addAttribute("message", "상품 등록이 완료되었습니다.");
		model.addAttribute("searchUrl", "/Productlist"); 
		
		return "admin/message";
		
//		return "ProductRegistration";
	}
	
	// 상품 목록 리스트
	@GetMapping("/Productlist")
	public String productList(Model model) {
		
		model.addAttribute("list", productService.productList());
		
		return "admin/Productlist";
	}
	
	// 상품 목록 리스트 테스트
	@GetMapping("/productlist")
	public String list(Model model, Pageable pageable, Sort sort) {
	
		
		model.addAttribute("productlist", productService.productList());
		
		return "admin/productlist";
	}
	
	
	// 상품 목록 리스트 (페이징처리 테스트)
//	@GetMapping("/productlist")
//	// @PageableDefault(page = 0, size = 5,)
////	, sort = "p_seq", direction = Sort.Direction.DESC <- 코드를 넣는 순간 페이지 활성화가 안됨, 이유 찾을것 
//	public String productlist(@PageableDefault(page = 0, size = 4, sort = "p_seq", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
//		
//		Page<Product> products = productService.getproductList(pageable);
//		
//		System.out.println("pageable==>" + pageable);
//		model.addAttribute("list", products);
//		
//		return "admin/Productlist";
//	}
	


	// 상품 수정 페이지 이동
	@GetMapping("/ProductModify/{p_seq}")
	public String productModify(@PathVariable("p_seq") Integer p_seq, Model model) {
		
		model.addAttribute("product", productService.productView(p_seq));
		
		return "admin/ProductModify";
	}
	
	// 상품 수정 (저장)
	@PostMapping("/product/update/{p_seq}")
	public String productUpdate(@PathVariable("p_seq") Integer p_seq, Product product, Model model, MultipartFile file) throws Exception{
		
		// 기존의 글에서 데이터값 가져오기
		Product productTemp = productService.productView(p_seq);
		productTemp.setP_name(product.getP_name());
		productTemp.setPrice1(product.getPrice1());
		productTemp.setPrice2(product.getPrice2());
		productTemp.setP_content(product.getP_content());
		productTemp.setP_image(product.getP_image());
		productTemp.setP_path(product.getP_path());
		
		productService.insertwrite(productTemp, file);
		
		model.addAttribute("message", "상품 수정이 완료되었습니다.");
		model.addAttribute("searchUrl", "/Productlist"); 
		
		return "admin/message";
	
	}
	
	// 상품 삭제 (Productlist 참고)
	
	
	@PostMapping("/productDelete")
	public String productDelete(@RequestParam Integer[] valueArr) {
		for(int i = 0; i < valueArr.length; i++) {
			productService.productDelete(valueArr[i]);	
		}
			
		return "redirect:/Productlist";
	}
	
	// 상품 조회
	


}
