package com.ezen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.domain.Product;
import com.ezen.service.ProductSercvice;

//admin 컨트롤러 (DB)

@Controller
public class AdminController {

	@Autowired
	private ProductSercvice productSercvice;
	
	// 대시보드로 이동
	@RequestMapping(value = "/Dashboards")
	public String Dashboards() {
		return "admin/Dashboards.html";
	}
	
	// 매니저 메인화면
	@RequestMapping(value = "/Manager")
	public String Manager() {
		return "admin/Manager.html";
	}
	
	// 매니저 상품 등록
	@RequestMapping(value = "/ProductCrystal")
	public String ProductCrystal() {
		return "admin/ProductCrystal.html";
	}
	
	// 상품관리 (카테고리별 매출현황)
	@RequestMapping(value = "/ProductManagement")
	public String ProductManagement() {
		return "admin/ProductManagement.html";
	}
	
	// 상품등록 (등록)
	@RequestMapping(value = "/ProductRegistration")
	public String ProductRegistration() {
		return "admin/ProductRegistration.html";
	}
	
	// 매출현황 (카테고리별 매출현황)
	@RequestMapping(value = "/SalesManagement")
	public String SalesManagement() {
		return "admin/SalesManagement.html";
	}
	
	// 펫시터/훈련사 승인대기목록
	@RequestMapping(value = "/UserApproval")
	public String UserApproval() {
		return "admin/UserApproval.html";
	}
	
	// 사용자 관리 (전체 사용자 목록 조회)
	@RequestMapping(value = "/UserManagement")
	public String UserManagement() {
		return "admin/UserManagement.html";
	}

	
	// 테스트 (상품 등록)
	@PostMapping("/productwrite")
	public String productwrite(Product product, Model model, MultipartFile file) throws Exception  {
		
		productSercvice.write(product, file);
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
	
//	// 상품 목록 리스트
	@GetMapping("/Productlist")
	public String productlist(Model model) {
		
		model.addAttribute("list", productSercvice.productList());
		
		return "admin/Productlist";
	}
	
	
	// 상품 목록 리스트 (페이징처리 테스트)
	@GetMapping("/Productlist")
	public String productlist(Model model, @PageableDefault(page = 0, size = 10, sort = "p_seq") Pageable pageable) {
		
		model.addAttribute("list", productSercvice.productList());
		
		return "admin/Productlist";
	}
	
	
	
	// 상품 수정 페이지 이동
	@GetMapping("/ProductModify/{p_seq}")
	public String productModify(@PathVariable("p_seq") Integer p_seq, Model model) {
		
		model.addAttribute("product", productSercvice.productView(p_seq));
		
		return "admin/ProductModify";
	}
	
	// 상품 수정 (저장)
	@PostMapping("/product/update/{p_seq}")
	public String productUpdate(@PathVariable("p_seq") Integer p_seq, Product product, Model model, MultipartFile file) throws Exception{
		
		// 기존의 글에서 데이터값 가져오기
		Product productTemp = productSercvice.productView(p_seq);
		productTemp.setP_name(product.getP_name());
		productTemp.setPrice1(product.getPrice1());
		productTemp.setPrice2(product.getPrice2());
		productTemp.setP_content(product.getP_content());
		productTemp.setP_image(product.getP_image());
		productTemp.setP_path(product.getP_path());
		
		productSercvice.write(productTemp, file);
		
		model.addAttribute("message", "상품 수정이 완료되었습니다.");
		model.addAttribute("searchUrl", "/Productlist"); 
		
		return "admin/message";
	
	}
	
	// 상품 삭제
	@PostMapping("/prodelete/delete")
	public String productDelete(Integer p_seq) {
		
		String[] ajaxMsg = request.getParameterValuse("valueArr");
		productSercvice.productDelete(p_seq);
		
		return "redirect:/productwrite";
	}
	
	
	


}
