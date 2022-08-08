package com.ezen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LayoutController {

	//로그인페이지이동
	@RequestMapping(value = "/Login")
	public String Login() {
		return "layout/Login.html";
	}
	
	@RequestMapping(value = "/UserJoin_Page")
	public String UserJoin_Page() {
		
		return "layout/UserJoin_Page.html";
	}
	
	@RequestMapping(value = "/IdpwFind_Page")
	public String IdpwFind_Page() {
		return "layout/IdpwFind_Page.html";
	}
}
