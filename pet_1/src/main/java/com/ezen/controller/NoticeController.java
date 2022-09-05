// 채완

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
import org.springframework.web.bind.annotation.RequestParam;
import com.ezen.entity.Notice;
import com.ezen.notice.service.NoticeService;

 
@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noService;
	
	
	
	// 공지사항 게시글 전체 조회
	@GetMapping("/Notice")
	public String noticeList(Notice notice, Model model,
			@PageableDefault(page = 0, size = 10, sort = "noSeq", direction = Sort.Direction.DESC) Pageable pageable) {
		
		Page<Notice> noticeList = noService.noticeList(notice, pageable);
		
		model.addAttribute("noticeList", noticeList);
		
		return "board/Notice";
	}

	
	// 공지사항 게시글 상세보기
	@GetMapping("/NoticeDetail/{noSeq}")
	public String noticeDetail(@PathVariable("noSeq") Integer noSeq, Model model) {
		
		model.addAttribute("notice", noService.noticeView(noSeq));
		
		return "board/Notice_Detail";
		
	}
	
	
	// 공지사항 게시글 등록 페이지 이동
	@GetMapping("/NoticeWriteForm")
	public String noticeWriteForm() {

		return "board/Notice_Write";		
	}
	
	
	// 공지사항 게시글 등록 처리
	@PostMapping("/NoticeWrite")
	public String insertNotice(Notice notice, Model model) {

		noService.insertNotice(notice);
		
		model.addAttribute(notice);
		
		return "redirect:/Notice";
	}

	
	// 공지사항 글 수정 처리
	@PostMapping("/Notice/update/{noSeq}")
	public String updateCustomers(@PathVariable("noSeq") Integer noSeq, Notice notice, Model model) {
		
		Notice noticeTemp = noService.noticeView(noSeq);
		
		noticeTemp.setNoTitle(notice.getNoTitle());
		noticeTemp.setNoContent(notice.getNoContent());
		noticeTemp.setNoRegDate(notice.getNoRegDate());
		
		noService.insertNotice(noticeTemp);
		
		model.addAttribute("message", "글 수정이 완료되었습니다.");
		model.addAttribute("searchUrl","/Notice");
		
		return "admin/message";
	}

	
	// 공지사항 글 삭제
	@GetMapping("/DeleteNotice")
	public String deleteNotice(@RequestParam(value="noSeq", required=false) Integer[] valueArr) {
		
		for(int i = 0; i < valueArr.length; i++) {
			noService.deleteNotice(valueArr[i]);	
		}
		
		return "redirect:/Notice";
	}

		

		
}
	   
	  

