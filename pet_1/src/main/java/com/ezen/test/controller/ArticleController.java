package com.ezen.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/alticle")
public class ArticleController {
	

	
	
		@GetMapping("/list") 
		public String getMessage(Model model) {
			model.addAttribute("testSTR", "이제부터는 타임리프로 사용.");
			return "article/list";
		}
	
	
	
	}
	

	
	

	
	

