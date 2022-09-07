// 채완 

package com.ezen.customerService.service;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ezen.Repository.CustomerServiceRepository;
import com.ezen.entity.CustomerService;

@Service
public class CustomersServiceImpl implements CustomersService {

	@Autowired
	private CustomerServiceRepository csRepo;


	// ----- 고객센터 게시글 전체 조회 -----
	@Override
	public Page<CustomerService> customersList(CustomerService customerService, Pageable pageable) {
		
		return csRepo.findAll(pageable);
	}

	// ----- 고객센터 글 상세 조회 -----
	@Override
	public CustomerService customersView(Integer csSeq) {
//		csRepo.UpdateCsCnt(csSeq);
		return csRepo.findById(csSeq).get();
	}
	
	// ----- 마이페이지 '본인' 작성글 조회 -----
	public Page<CustomerService> findCustomerListByMemberId(String memberId, Pageable pageable) {
		
		return (Page<CustomerService>)csRepo.findCustomerListByMemberId(memberId, pageable);
	}

	// ----- 고객센터 글 등록 -----
	@Override
	public void insertCustomers(CustomerService customers) {
		
		csRepo.save(customers);
	}

	// ----- 고객센터 글 수정 -----
	@Override
	public void updateCustomers(CustomerService customers, Principal principal) {
		
		CustomerService findCustomers = csRepo.findById(customers.getCsSeq()).get();
		
		findCustomers.setCsTitle(customers.getCsTitle());
		findCustomers.setCsContent(customers.getCsContent());

		
		csRepo.save(findCustomers);
	}

	// ----- 고객센터 글 삭제 -----
	@Override
	public void deleteCustomers(Integer csSeq) {
		
		csRepo.deleteById(csSeq);
	}

}


