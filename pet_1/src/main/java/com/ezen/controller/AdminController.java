package com.ezen.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.Repository.CartRepository;
import com.ezen.Repository.CustomerServiceRepository;
import com.ezen.Repository.OrdersDetailRepository;
import com.ezen.Repository.OrdersRepository;
import com.ezen.Repository.ReservationsRepository;
import com.ezen.entity.Cart;
import com.ezen.entity.CustomerService;
import com.ezen.entity.Member;
import com.ezen.entity.OrdersDetail;
import com.ezen.entity.Product;
import com.ezen.entity.Reservations;
import com.ezen.member.service.MemberService;
import com.ezen.ordersdetail.service.OrdersDetailService;
import com.ezen.product.service.ProductService;

@Controller
public class AdminController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private MemberService memberService;
	
	//데이터 강제 입력
	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	private OrdersDetailRepository ordersDetailRepository;
	
	@Autowired
	private OrdersDetailService ordersDetailService;
	
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ReservationsRepository reservationsRepository;

	@Autowired
	private CustomerServiceRepository customerServiceRepository;
	
	
	// 매니저 메인화면
	@RequestMapping("/admin")
	public String Manager() {
		return "/admin/admin";
	}
	
//	// 매출현황 (날짜별 매출현황) 날짜 조회 넘겨주기
//	@GetMapping("/SalesManagement")
//	public String salesDatecheck(@RequestParam(value = "startDate", required = false) String startDate, 
//								 @RequestParam(value = "endDate", required = false) String endDate,
//								 String searchKeyword, Model model, OrdersDetail ordersDetail, 
//								 @PageableDefault(page = 0, size = 10, sort = "odSeq", direction = Sort.Direction.ASC) Pageable pageable) {
//		searchKeyword = (searchKeyword == null) ? "" : searchKeyword;
////		startDate = (startDate == null) ? "" : startDate;
////		endDate = (endDate == null) ? "" : endDate;
//		System.out.println(" ●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●● 여기까지만이라도 들어와주세열 플리쥬 ");
//		System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>startDate " + startDate);
////		list = ordersDetailService.ordersDetailList(pageable);
//		
//		LocalDateTime startDateTime = null;
//		LocalDateTime endDateTime = null;
//		
//	    // 입력된 날짜가 빈값일 때
//	    if (startDate == "" || startDate == null) {
//	    	startDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));
//	    } else {
//		    // String으로 들어오는 날짜 데이터 변환
//	    	startDateTime = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atTime(0, 0, 0);
//	    }
//	    if (endDate == "" || endDate == null) {
//	    	endDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
//	    } else {
//		    endDateTime = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atTime(23, 59, 59);
//	    }
//	    
////	    Page<Orders> list = ordersRepository.findAllByOrderDateBetween(startDateTime, endDateTime, pageable);
//	    Page<OrdersDetail> list = ordersDetailRepository.findAllByOdDateBetween(startDateTime, endDateTime, pageable);
//	    
//	    System.out.println(list);
//	    System.out.println(list.getContent());
//	    System.out.println(list.getTotalElements());
//		
////		System.out.println("==========>" + startDateTime);
////		System.out.println(LocalDate.now());
////	    System.out.println("==========>" + endDateTime);
////		System.out.println("psalesManagementList333=============" + list.getContent());
//	    
//
//		// 페이징 처리 넘겨주기
//		model.addAttribute("list", list);
//		model.addAttribute("pKind", searchKeyword);
//		model.addAttribute("startDate", startDate);
//		model.addAttribute("endDate", endDate);
//
//		return "admin/SalesManagement";
//	}
	
	// 매출현황 (날짜별 매출현황) 날짜 조회 넘겨주기
	@GetMapping("/SalesManagement")
	public String salesDatecheck(@RequestParam(value = "startDate", required = false) String startDate, 
								 @RequestParam(value = "endDate", required = false) String endDate,
								 @RequestParam(value = "searchKeyword", required = false) String searchKeyword, Model model, OrdersDetail ordersDetail, 
								 @PageableDefault(page = 0, size = 10, sort = "odSeq", direction = Sort.Direction.ASC) Pageable pageable) {
		searchKeyword = (searchKeyword == null) ? "" : searchKeyword;
		LocalDateTime startDateTime = null;
		LocalDateTime endDateTime = null;
		Page<OrdersDetail> list = null;

	    // 입력된 날짜가 빈값일 때
		if (startDate == "" || startDate == null) {
			startDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			startDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));
		} else {
		    // String으로 들어오는 날짜 데이터 변환
			startDateTime = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atTime(0, 0, 0);
		}
		if (endDate == "" || endDate == null) {
			endDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			endDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
		} else {
			endDateTime = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atTime(23, 59, 59);
		}

		if (Objects.equals("", searchKeyword)) {
			list = ordersDetailRepository.findAllByOdDateBetween(startDateTime, endDateTime, pageable);
		} else {
			list = ordersDetailRepository.findAllByOdDateBetweenAndPkind(startDateTime, endDateTime, searchKeyword, pageable);
		}
			
		// 페이징 처리 넘겨주기
		model.addAttribute("list", list);
		model.addAttribute("pKind", searchKeyword);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);

		return "admin/SalesManagement";
	}
	
	// 대시보드로 이동
	@RequestMapping(value = "/Dashboards")
	public String Dashboards(Model model) {
		List<Cart> cartList = cartRepository.findByResult('1');
		List<OrdersDetail> odList = ordersDetailRepository.findByResult('1');
		List<Reservations> resList = reservationsRepository.findByRsStatus("Y");
		List<CustomerService> customerList = customerServiceRepository.findAll();

		model.addAttribute("cartCount", cartList.size());
		model.addAttribute("orderDetailCount", odList.size());
		model.addAttribute("resCount", resList.size());
		model.addAttribute("customerCount", customerList.size());

		return "/admin/Dashboards";
	}

	// 대시보드
	@GetMapping("/DashboardChart")
	@ResponseBody
	public String DashboardChart(HttpServletRequest request) {
		String jsonData = ordersDetailService.findOrderDetailChartData();

		return jsonData;
	}

	// 상품등록 (등록 페이지 이동)
	@RequestMapping(value = "/ProductRegistration")
	public String ProductRegistration(Integer pSeq, Pageable pageable) {
		return "admin/ProductRegistration.html";
	}
	
	// 상품관리 (상품 등록)
	@PostMapping("/Productwrite")
	public String Productwrite(Product product, Model model, MultipartFile file) throws Exception {

		productService.insertwrite(product, file);

		model.addAttribute("message", "상품 등록이 완료되었습니다.");
		model.addAttribute("searchUrl", "/Productlist");

		return "admin/message";	

	}
	
	// 상품등록 시 주문테이블에 강제 insert 하기 위해 임시로 사용 
//	@PostMapping("/Productwrite")
//	public String Productwrite(Product product, Model model, MultipartFile file, Principal principal) throws Exception {
//		
//		productService.insertwrite(product, file);
//		System.out.println("번호 : " + product.getPSeq());
//
//		model.addAttribute("message", "상품 등록이 완료되었습니다.");
//		model.addAttribute("searchUrl", "/Productlist");
//		
//		Orders orders = new Orders();
//		Member member = memberService.getMember(principal.getName());
//		// productTemp.setpSeq(product.getpSeq());
//		orders.setMember(member);
//		orders.setOrderRce("컴포즈");
//		orders.setOrderTel("01011111112");
//		orders.setOrderAddr1("서울시 금천구");
//		ordersRepository.save(orders);
	
	
//		
//		OrdersDetail od = new OrdersDetail();
//		od.setQuantity(10);
//		od.setOrder(orders);
//		od.setProduct(product);
//		ordersDetailRepository.save(od);
//
//		return "admin/message";
//
//	}
	
	// 상품관리 리스트 (페이징처리)
	@GetMapping("/Productlist")
	public String productList(String searchKeyword, Model model,
			@PageableDefault(page = 0, size = 10, sort = "pSeq", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Product> list = null;
		
		if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
			list = productService.productList(pageable); // 기존에 보여주는 리스트
		} else {
			list = productService.productSerchList(searchKeyword, pageable); // 검색 기능이 포함된 리스트
		}
		// 페이징 처리 넘겨주기
		model.addAttribute("list", list);
		model.addAttribute("pKind", searchKeyword);

		return "admin/Productlist";
	}

	// 상품관리 (수정페이지 이동)
	@GetMapping("/ProductModify/{pSeq}")
	public String ProductModify(@PathVariable("pSeq") Integer pSeq, Model model) {

		model.addAttribute("product", productService.productView(pSeq));

		return "admin/ProductModify";
	}

	// 상품관리 (상품 수정 저장)
	@PostMapping("/product/update/{pSeq}")
	public String productUpdate(@PathVariable("pSeq") Integer pSeq, Product product, Model model, MultipartFile file)
			throws Exception {

		// 기존의 글에서 데이터값 가져오기
		Product productTemp = productService.productView(pSeq);
		// productTemp.setpSeq(product.getpSeq());
		productTemp.setPKind(product.getPKind());
		productTemp.setPName(product.getPName());
		productTemp.setPrice1(product.getPrice1());
		productTemp.setPrice2(product.getPrice2());
		productTemp.setPrice3(product.getPrice3());
		productTemp.setPContent(product.getPContent());
		productTemp.setPImage(productTemp.getPImage());
		productTemp.setPPath(productTemp.getPPath());

		productService.insertwrite(productTemp, file);

		model.addAttribute("message", "상품 수정이 완료되었습니다.");
		model.addAttribute("searchUrl", "/Productlist");

		return "admin/message";

	}

	// 상품관리 상품 삭제 (Productlist 참고)
	@PostMapping("/productDelete")
	public String productDelete(@RequestParam Integer[] valueArr) {
		for (int i = 0; i < valueArr.length; i++) {
			productService.productDelete(valueArr[i]);
		}

		return "redirect:/Productlist";
	}
	
	// 전체 사용자 목록조회_sy
    @GetMapping("/UserManagement")
    public String userManagementlist(Model model, String searchKeyword,
    							     @PageableDefault(page = 0, size = 5, sort = "memberId", direction = Sort.Direction.ASC) Pageable pageable) {
    	Page<Member> list = null;
		
    	if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
    		list = memberService.userManagementList(pageable); // 기존에 보여주는 리스트
		} else {
			list = memberService.userManagemenSerchList(searchKeyword, pageable); // 검색 기능이 포함된 리스트
		}
		
    	model.addAttribute("list", list);
    	model.addAttribute("memberType", searchKeyword);
    	
    	return "admin/UserManagement";
    }
	    
    // 펫시터/훈련사 프로필 승인 대기_sy
    @GetMapping("/userApproval")
    public String userApprovallist(Model model, @PageableDefault(page = 0, size = 5, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
    
    	
    	Page<Member> list = memberService.memberTypeList(pageable);
    	
    	model.addAttribute("list", list);
    	
    	return "admin/UserApproval";
    }
    
    // 펫시터/훈련사 프로필 승인_sy
    @PostMapping("/userProApproval")
	public String userProApproval(@RequestParam String[] valueArr) {
    	
    	memberService.userProApproval(valueArr);

		return "redirect:/userApproval";
	}
	
    

}
