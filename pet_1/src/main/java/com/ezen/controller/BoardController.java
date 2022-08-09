package com.ezen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	
	@RequestMapping(value = "/center_detail")
	public String center_detail() {
		return "board/center_detail.html";
	}
	
	@RequestMapping(value = "/center")
	public String center() {
		return "board/center.html";
	}
	
	@RequestMapping(value = "/center_write")
	public String center_write() {
		return "board/center_write.html";
	}
	
	@RequestMapping(value = "/menu")
	public String menu() {
		return "board/menu.html";
	}
	
	@RequestMapping(value = "/petShop_Details")
	public String petShop_DetailsPage() {
		return "board/petShop_Details.html";
	}
	
	@RequestMapping(value = "/sitter")
	public String sitter() {
		return "board/sitter.html";
	}
	
	@RequestMapping(value = "/sitter1")
	public String sitter1() {
		return "board/sitter1.html";
	}
	
	@RequestMapping(value = "/sitter2")
	public String sitter2() {
		return "board/sitter2.html";
	}
	
	@RequestMapping(value = "/sitterBooking")
	public String sitterBooking() {
		return "board/sitterBooking.html";
	}
	
	@RequestMapping(value = "/sitterProfile")
	public String sitterProfile() {
		return "board/sitterProfile.html";
	}
	
	@RequestMapping(value = "/trainer")
	public String trainer() {
		return "board/trainer.html";
	}
	
	@RequestMapping(value = "/trainer1")
	public String trainer1() {
		return "board/trainer1.html";
	}
	
	@RequestMapping(value = "/trainerBooking")
	public String trainerBooking() {
		return "board/trainerBooking.html";
	}
	
	@RequestMapping(value = "/trainerProfile")
	public String trainerProfile() {
		return "board/trainerProfile.html";
	}
}
