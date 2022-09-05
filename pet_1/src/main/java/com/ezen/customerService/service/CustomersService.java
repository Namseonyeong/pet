// 채완 

package com.ezen.customerService.service;

import java.security.Principal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ezen.entity.CustomerService;

public interface CustomersService {

	// ----- 고객센터 게시글 전체 조회 -----
	Page<CustomerService> customersList(CustomerService customerService, Pageable pageable);
	
	// ----- 고객센터 글 상세 조회 -----
	CustomerService customersView(Integer csSeq);
	
	// ----- 마이페이지 '본인' 작성글 조회 -----
	Page<CustomerService> findCustomerListByMemberId(String memberId, Pageable pageable);
	
	// ----- 고객센터 글 등록 -----
	void insertCustomers(CustomerService customers);

	// ----- 고객센터 글 수정 -----
	void updateCustomers(CustomerService customers, Principal principal);
	
	// ----- 고객센터 글 삭제 -----
	void deleteCustomers(Integer csSeq);
	

	

	

	
}
 
