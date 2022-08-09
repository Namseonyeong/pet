package com.ezen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	
	@RequestMapping(value = "/Center_detail_Page")
	public String Center_detail_Page() {
		return "board/Center_detail_Page.html";
	}
	
	@RequestMapping(value = "/Center_Page")
	public String Center_Page() {
		return "board/Center_Page.html";
	}
	
	@RequestMapping(value = "/Center_write_Page")
	public String Center_write_Page() {
		return "board/Center_write_Page.html";
	}
	
	@RequestMapping(value = "/Menu")
	public String Menu() {
		return "board/Menu.html";
	}
	
	@RequestMapping(value = "/PetShop_DetailsPage")
	public String PetShop_DetailsPage() {
		return "board/PetShop_DetailsPage.html";
	}
	
	@RequestMapping(value = "/Sitter_Page")
	public String Sitter_Page() {
		return "board/Sitter_Page.html";
	}
	
	@RequestMapping(value = "/Sitter1_Page")
	public String Sitter1_Page() {
		return "board/Sitter1_Page.html";
	}
	
	@RequestMapping(value = "/Sitter2_Page")
	public String Sitter2_Page() {
		return "board/Sitter2_Page.html";
	}
	
	@RequestMapping(value = "/SitterBooking_Page")
	public String SitterBooking_Page() {
		return "board/SitterBooking_Page.html";
	}
	
	@RequestMapping(value = "/SitterProfile_Page")
	public String SitterProfile_Page() {
		return "board/SitterProfile_Page.html";
	}
	
	@RequestMapping(value = "/Trainer_Page")
	public String Trainer_Page() {
		return "board/Trainer_Page.html";
	}
	
	@RequestMapping(value = "/Trainer1_Page")
	public String Trainer1_Page() {
		return "board/Trainer1_Page.html";
	}
	
	@RequestMapping(value = "/TrainerBooking_Page")
	public String TrainerBooking_Page() {
		return "board/TrainerBooking_Page.html";
	}
	
	@RequestMapping(value = "/TrainerProfile_Page")
	public String TrainerProfile_Page() {
		return "board/TrainerProfile_Page.html";
	}
}
