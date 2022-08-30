package com.ezen.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import com.ezen.Repository.OrdersDetailRepository;
import com.ezen.Repository.OrdersRepository;
import com.ezen.entity.Member;
import com.ezen.entity.Orders;
import com.ezen.entity.OrdersDetail;
import com.ezen.entity.Product;
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
	
	// 매니저 메인화면
	@RequestMapping("/admin")
	public String Manager() {
		return "/admin/admin";
	}
	
	
//	// 매출현황 (카테고리별 매출현황) / 목록 보여주기 test
//	@GetMapping("/SalesManagement")
//	public String salesManagementList(String searchKeyword, String startDate, String endDate, Model model, OrdersDetailSy ordersDetailSy, LocalDateTime localdate,
//			@PageableDefault(page = 0, size = 10, sort = "odSeq", direction = Sort.Direction.ASC) Pageable pageable) {
//		searchKeyword = (searchKeyword == null) ? "" : searchKeyword;
//		startDate = (startDate == null) ? "" : startDate;
//		endDate = (endDate == null) ? "" : endDate;
//	
//		
////		 LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(0,0,0));
////		 LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));
////		 ordersRepository.findAllByOrderDateBetween(startDatetime, endDatetime);
//		
////		if (!Strings.isNullOrEmpty(param.getRegisterdTime())) {
////            LocalDateParser localDateParser = new LocalDateParser(param.getRegisterdTime());
////            builder.and(qTest.registerdTime.between(localDateParser.startDate(), localDateParser.endDate()));
////        } else {
////            // default : toDay >=
////            LocalDate currentDate = LocalDate.now();
////            builder.and(qTest.registerdTime.gt(currentDate.atStartOfDay()));
////        }
//		
//	
//		
//		Page<OrdersDetailSy> list = null;
////		list = ordersDetailService.ordersDetailList(pageable);
//		if (localdate == null) {
//			list = ordersDetailService.ordersDetailList(pageable);
//		} else {
//			list =  ordersDetailService.ordersDetailSerchList(searchKeyword, pageable);
//		}
//		
////		System.out.println("salesManagementList===============" + list.hasContent());
////		System.out.println("salesManagementList222===============" + list.getTotalElements());
//		System.out.println("psalesManagementList333=============" + list.getContent());
////		System.out.println("order_date=============" + list.getorderDate());
//		
//		// 페이징 처리 넘겨주기
//		model.addAttribute("list", list);
//		model.addAttribute("pKind", searchKeyword);
//		model.addAttribute("startDate", startDate);
//		model.addAttribute("endDate", endDate);
//		
//
//		return "admin/SalesManagement";
//		
//	}
	
	
	// 매출현황 (날짜별 매출현황) 날짜 조회 넘겨주기
//	@GetMapping("/SalesManagement")
//	public String salesDatecheck(@RequestParam(value = "startDate", required = false) String startDate,
//									  @RequestParam(value = "endDate", required = false) String endDate,
//			String searchKeyword, Model model, OrdersDetailSy ordersDetailSy, 
//			@PageableDefault(page = 0, size = 10, sort = "odSeq", direction = Sort.Direction.ASC) Pageable pageable) {
////		searchKeyword = (searchKeyword == null) ? "" : searchKeyword;
////		startDate = (startDate == null) ? "" : startDate;
////		endDate = (endDate == null) ? "" : endDate;
//		System.out.println(" ●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●● 여기까지만이라도 들어와주세열 플리쥬 ");
//		System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>startDate " + startDate);
//		Page<OrdersDetailSy> list = null;
////		list = ordersDetailService.ordersDetailList(pageable);
//		
//	    // 입력된 날짜가 빈값일 때
//	    if (startDate == "") {
//	    	startDate = "00010101";
//	    }
//	    if (endDate == "") {
//	    	endDate = "99991231";
//	    }
//
//	    // String으로 들어오는 날짜 데이터 변환
//	    LocalDateTime startDateTime = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyyMMdd")).atTime(0, 0, 0);
//	    LocalDateTime endDateTime = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyyMMdd")).atTime(23, 59, 59);
//		
//		System.out.println("psalesManagementList333=============" + list.getContent());
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
			String searchKeyword, Model model, OrdersDetail ordersDetail, 
			@PageableDefault(page = 0, size = 10, sort = "odSeq", direction = Sort.Direction.ASC) Pageable pageable) {
//		searchKeyword = (searchKeyword == null) ? "" : searchKeyword;
//		startDate = (startDate == null) ? "" : startDate;
//		endDate = (endDate == null) ? "" : endDate;
		System.out.println(" ●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●● 여기까지만이라도 들어와주세열 플리쥬 ");
		System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>startDate " + startDate);
		Page<OrdersDetail> list = null;
//		list = ordersDetailService.ordersDetailList(pageable);
		
		LocalDateTime startDateTime = null;
		LocalDateTime endDateTime = null;
		
	    // 입력된 날짜가 빈값일 때
	    if (startDate == "" || startDate == null) {
	    	startDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));
	    } else {
	    	startDateTime = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atTime(0, 0, 0);
	    }
	    if (endDate == "" || endDate == null) {
	    	endDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
	    } else {
		    endDateTime = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atTime(23, 59, 59);
	    }
	    
	    List<Orders> orders = ordersRepository.findAllByOrderDateBetween(startDateTime, endDateTime);
	    System.out.println(orders.size());

	    // String으로 들어오는 날짜 데이터 변환
		
		System.out.println("==========>" + startDateTime);
		System.out.println(LocalDate.now());
	    System.out.println("==========>" + endDateTime);
//		System.out.println("psalesManagementList333=============" + list.getContent());
	    

		// 페이징 처리 넘겨주기
		model.addAttribute("list", list);
		model.addAttribute("pKind", searchKeyword);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);

		return "admin/SalesManagement";
	}
	
	

	// 대시보드로 이동
	@RequestMapping(value = "/Dashboards")
	public String Dashboards() {
		return "/admin/Dashboards";
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
//	
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
	
	
	// 사용자 관리 (전체 사용자 목록 조회)
//		@PostMapping("/UserManagement")
//		public String UserManagement() {
//			return "admin/UserManagement";
//		}
	
	// 기존 리스트
//	@GetMapping("/UserManagement")
//	public String allMemberList(@RequestParam("memberType") String memberType, Model model) {
//		System.out.println("===========>" + memberType );
//		List<Member> memberList = productService.findBymemberType(memberType);
//		model.addAttribute("memberList", memberList);
//		
//		return "admin/UserManagement";
//	}
		
		// 사용자 리스트 (페이징처리)
		@GetMapping("/UserManagement")
		public String userManagementlist(String searchKeyword, Model model,
				@PageableDefault(page = 0, size = 10, sort = "memberId", direction = Sort.Direction.ASC) Pageable pageable) {
			Page<Member> list = null;
//			String memberType.value("A") == " 일반화원"
			if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
				list = productService.userManagementList(pageable); // 기존에 보여주는 리스트
			} else {
				list = productService.userManagemenSerchList(searchKeyword, pageable); // 검색 기능이 포함된 리스트
			}

			
			// 페이징 처리 넘겨주기
			model.addAttribute("list", list);
			model.addAttribute("memberType", searchKeyword);

			return "admin/UserManagement";
		}
		
		

		// 펫시터/훈련사 승인대기목록
//		@GetMapping(value = "/userApproval")
//		public String UserApproval() {
//			return "admin/UserApproval";
//		}
		
//		// 펫시터,훈련사 승인 대기 Member createdDate = 회원가입 순
		@GetMapping("/userApproval")
		public String userApprovallist(String searchKeyword, Model model, Member member,
				@PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
			
//			System.out.println("=======================================>" + memberType);
			Page<Member> list = null;
			if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
				list = productService.userApprovaList(pageable); // 기존에 보여주는 리스트
			} else {
				list = productService.userApprovaSerchList(searchKeyword, pageable); // 검색 기능이 포함된 리스트
			}

			// 페이징 처리 넘겨주기
			model.addAttribute("list", list);
			model.addAttribute("memberType", searchKeyword);

			return "admin/UserApproval";
		}
		
//		@GetMapping("/userApproval")
//		public String userApprovallist(@RequestParam("memberType") String memberType, Model model) {
////			System.out.println(memberType.getClass().getName()); 
//			List<Member> userApprovallist = productService.findBymemberType(memberType);
//			System.out.println("==========controller=====>>" + userApprovallist);
////			System.out.println("=============== 여기서 부터 못들고 옴 ==>" + memberType ) ;
////			model.addAttribute("list", userApprovallist);
//			
//			
//			return "admin/UserApproval";
//		}

		
//		// 승인목록 test
//	    @GetMapping("/userApproval")
//	    public String userApprovallist(@RequestParam("memberType") char memberType, Model model) {
//	      
//	       List<Member> userApprovallist = memberService.findBymemberType(memberType);
//	       model.addAttribute("userApprovallist", userApprovallist);
//	       
//	       return "admin/UserApproval";
//	    }


}
