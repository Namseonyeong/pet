package com.ezen.controller;

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

import com.ezen.entity.Member;
import com.ezen.entity.Product;
import com.ezen.member.service.MemberService;
import com.ezen.product.service.ProductService;

//admin 컨트롤러 (DB)

@Controller
public class AdminController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private MemberService memberService;

	// 대시보드로 이동
	@RequestMapping(value = "/Dashboards")
	public String Dashboards() {
		return "/admin/Dashboards";
	}

	// 매니저 메인화면
	@RequestMapping("/admin")
	public String Manager() {
		return "/admin/admin";
	}

//	// 상품관리 (카테고리별 매출현황)
//	@RequestMapping(value = "/ProductManagement")
//	public String ProductManagement() {
//		return "admin/ProductManagement.html";
//	}

//	// 상품등록 (등록)
	@RequestMapping(value = "/ProductRegistration")
	public String ProductRegistration() {
		return "admin/ProductRegistration.html";
	}

	// 매출현황 (카테고리별 매출현황)
	@RequestMapping(value = "/SalesManagement")
	public String SalesManagement() {
		return "admin/SalesManagement";
	}

	
	// 테스트 (상품 등록)
	@PostMapping("/Productwrite")
	public String Productwrite(Product product, Model model, MultipartFile file) throws Exception {

		productService.insertwrite(product, file);
		System.out.println("번호 : " + product.getPSeq());

//		System.out.println("분류 : " + product.getPKind());
//		System.out.println("이름 : " + product.getpName()); 
//		System.out.println("원가 : " + product.getPrice1());
//		System.out.println("판매가 : " + product.getPrice2());
//		System.out.println("순수익 : " + product.getPrice3());
//		System.out.println("상세정보 : " + product.getPContent());
//		System.out.println("이미지 : " + product.getPImage());
//		
		model.addAttribute("message", "상품 등록이 완료되었습니다.");
		model.addAttribute("searchUrl", "/Productlist");

		return "admin/message";

	}

	// 상품 목록 리스트
//	@GetMapping("/Productlist")
//	public String Productlist(Model model) {
//		
//		model.addAttribute("list", productService.productList());
//		
//		return "admin/Productlist";
//	}

	// 상품 목록 리스트 (페이징처리)
	@GetMapping("/Productlist")
	public String productList(String searchKeyword, Model model,
			@PageableDefault(page = 0, size = 10, sort = "pSeq", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Product> list = null;
		
		if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
			list = productService.productList(pageable); // 기존에 보여주는 리스트
		} else {
			list = productService.productSerchList(searchKeyword, pageable); // 검색 기능이 포함된 리스트
		}
		System.out.println("==============================" + list );
		// 페이징 처리 넘겨주기
		model.addAttribute("list", list);
		model.addAttribute("pKind", searchKeyword);

		return "admin/Productlist";
	}

	// 상품 수정 페이지 이동
	@GetMapping("/ProductModify/{pSeq}")
	public String ProductModify(@PathVariable("pSeq") Integer pSeq, Model model) {

		model.addAttribute("product", productService.productView(pSeq));

		return "admin/ProductModify";
	}

	// 상품 수정 (저장)
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

	// 상품 삭제 (Productlist 참고)

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
//		@RequestMapping(value = "/userApproval")
//		public String UserApproval() {
//			return "admin/UserApproval.html";
//		}
		
//		// 펫시터,훈련사 승인 대기 Member createdDate = 회원가입 순
//		@GetMapping("/userApproval")
//		public String userApprovallist(String searchKeyword, Model model, @RequestParam("memberType") char memberType,
//				@PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
//			
//			System.out.println("=======================================>" + memberType);
//			Page<Member> list = null;
//			if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
//				list = productService.userApprovaList((char) 0, pageable); // 기존에 보여주는 리스트
//			} else {
//				list = productService.userApprovaSerchList((char) 0, searchKeyword, pageable); // 검색 기능이 포함된 리스트
//			}
//
//			// 페이징 처리 넘겨주기
//			model.addAttribute("list", list);
//			model.addAttribute("memberType", searchKeyword);
//
//			return "admin/UserApproval";
//		}
		
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
