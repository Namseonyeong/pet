// 채완

package com.ezen.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ezen.Repository.MemberRepository;
import com.ezen.customerService.service.CustomersService;
import com.ezen.entity.CustomerService;
import com.ezen.entity.Member;

 
@Controller
public class CustomerServiceController {
	
	@Autowired
	private CustomersService csService;
	@Autowired
	private MemberRepository memberRepo;
	
	
	
	// 고객센터 게시글 전체 조회
	@GetMapping("/CustomersService")
	public String customersList(CustomerService customers, Model model,
			@PageableDefault(page = 0, size = 10, sort = "csSeq", direction = Sort.Direction.DESC) Pageable pageable) {
		
		Page<CustomerService> customersList = csService.customersList(customers, pageable);
		
		model.addAttribute("customersList", customersList);
		
		return "board/Customers_Service";
	}

	
	// 고객센터 게시글 상세보기
	@GetMapping("/CustomerDetail/{csSeq}")
	public String customerDetail(@PathVariable("csSeq") Integer csSeq, Model model) {
		
		model.addAttribute("customerService", csService.customersView(csSeq));
		
		return "board/Customers_Detail";
		
	}
	
	
	// 마이페이지 '본인' 작성 글 전체 조회
	@GetMapping("/MyCustomerList")
	public String myCustomerList(CustomerService customers, Model model, Principal principal,
			@PageableDefault(page = 0, size = 10, sort = "csSeq", direction = Sort.Direction.DESC) Pageable pageable) {
		
		Page<CustomerService> myCustomerList = csService.findCustomerListByMemberId(principal.getName(), pageable);
		
		model.addAttribute("myCustomerList", myCustomerList);
		
		return "member/MyPage_getboardlist";
	}
	
	
	// 마이페이지 '본인' 게시글 상세보기
	@GetMapping("/MyCustomerDetail/{csSeq}")
	public String myCustomerDetail(@PathVariable("csSeq") Integer csSeq, Model model) {
		
		model.addAttribute("customerService", csService.customersView(csSeq));
		
		return "board/MyCustomer_detail";
		
	}
	
	
	// 고객센터 게시글 등록 페이지 이동
	@GetMapping("/customersWriteForm")
	public String customersWriteForm() {

		return "board/Customers_Write";		
	}
	
	
	// 고객센터 게시글 등록 처리
	@PostMapping("/customersWrite")
	public String insertCustomers(CustomerService customers, Principal principal) {
		Member member = memberRepo.findByMemberId(principal.getName()).get();
		customers.setMember(member);
		csService.insertCustomers(customers);
		
		return "redirect:/CustomersService";
		
	}

	
	// 글 수정 처리
	@PostMapping("/customerService/update/{csSeq}")
	public String updateCustomers(@PathVariable("csSeq") Integer csSeq, CustomerService customers, Model model) {
		
		CustomerService customerTemp = csService.customersView(csSeq);
		
		customerTemp.setCsTitle(customers.getCsTitle());
		customerTemp.setCsContent(customers.getCsContent());

		
		csService.insertCustomers(customerTemp);
		
		model.addAttribute("message", "글 수정이 완료되었습니다.");
		model.addAttribute("searchUrl","/MyCustomerList");
		
		return "board/message";
	}

	
	// 게시글 삭제
	@GetMapping("/DeleteCustomers")
	public String deleteCustomers(@RequestParam(value="csSeq", required=false) Integer[] valueArr) {
		
		for(int i = 0; i < valueArr.length; i++) {
			csService.deleteCustomers(valueArr[i]);	
		}
		
		return "redirect:/MyCustomerList";
	}

		

}
	   
	  

