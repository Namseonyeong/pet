//package com.ezen.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.ezen.Repository.ProductRepository;
//import com.ezen.entity.Member;
//import com.ezen.entity.Product;
//import com.ezen.product.service.ProductService;
//
//@Controller
//public class ProductController {
//	@Autowired
//	private ProductService productService;
//	@Autowired
//	private ProductRepository productRepository;
//	
//	@RequestMapping("/getProductList")
//	public String getProductList(@ModelAttribute("member")Member member,
//								Model model, Pageable product) {
//		Page<Product> productList = productService.productList(product);
//		
//		model.addAttribute("productList", productList);
//		
//		return "getProductList";
//		
//	}
//	
//	@GetMapping("/category")
//	public String getProductKindList(@RequestParam("pKind") String pKind, Model model) {
//		System.out.println("getProductKindList() ====>pKind="+pKind);
//		
//		Pageable pageable = PageRequest.of(0, 5);
//		
//		Page<Product> productfindKindList = productService.productfindByPKind(pKind, pageable);
//		
//		List<Product> PKind = productfindKindList.getContent();
//		
//		
//		for(Product p : PKind) {
//			System.out.println(p);
//		}
//		model.addAttribute("productList", PKind);
//		
//		//https://sharekim-dev.tistory.com/29 참고
//		
//		return "board/Menu.html";
//	}
//	
//}
