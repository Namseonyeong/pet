package com.ezen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.entity.Product;
import com.ezen.product.service.ProductService;

@Controller
public class BoardController {
	
	@Autowired
	private ProductService productservice;
	
	@RequestMapping(value = "/Center_detail")
	public String Center_detail() {
		return "board/Center_detail.html";
	}

	@RequestMapping(value = "/Center")
	public String Center() {
		return "board/Center.html";
	}

	@RequestMapping(value = "/center_write")
	public String Center_write() {
		return "board/Center_write.html";
	}

	@RequestMapping(value = "/PetShop_Details")
	public String PetShop_Details() {
		return "board/PetShop_Details.html";
	}

/*	@RequestMapping(value = "/Sitters")
	public String Sitter() {
		return "board/Sitter";
	}
*/
	@RequestMapping(value = "/Sitter1")
	public String Sitter1() {
		return "board/Sitter1.html";
	}

	@RequestMapping(value = "/Sitter2")
	public String Sitter2() {
		return "board/Sitter2.html";
	}
/*
	@RequestMapping(value = "/SitterBooking")
	public String SitterBooking() {
		return "board/SitterBooking.html";
	}

	@RequestMapping(value = "/SitterProfile")
	public String SitterProfile() {
		return "board/SitterProfile.html";
	}

	@RequestMapping(value = "/Trainer")
	public String Trainer() {
		return "board/Trainer.html";
	}
*/
	@RequestMapping(value = "/Trainer1")
	public String Trainer1() {
		return "board/Trainer1.html";
	}
/*
	@RequestMapping(value = "/TrainerBooking")
	public String TrainerBooking() {
		return "board/TrainerBooking.html";
	}

	@RequestMapping(value = "/TrainerProfile")
	public String TrainerProfile() {
		return "board/TrainerProfile.html";
	}
*/
	
	
	
	
	//Menu.html에 상품리스트출력
	@RequestMapping(value = "/Menu")
	public String Menu(Model model, Pageable product) {	
		Page<Product> productList = productservice.productList(product);
		model.addAttribute("productList", productList);
		return "board/Menu.html";
	}
}
