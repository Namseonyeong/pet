// 채완 

package com.ezen.notice.service;

import java.security.Principal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ezen.entity.Notice;

public interface NoticeService {

	// ----- 공지사항 게시글 전체 조회 -----
	Page<Notice> noticeList(Notice notice, Pageable pageable);
	
	// ----- 공지사항 글 상세 조회 -----
	Notice noticeView(Integer noSeq);
	
	// ----- 마이페이지 '본인' 작성글 조회 -----
//	Page<CustomerService> findCustomerListByMemberId(String memberId, Pageable pageable);
	
	// ----- 공지사항 글 등록 -----
	void insertNotice(Notice notice);

	// ----- 공지사항 글 수정 -----
	void updateNotice(Notice notice, Principal principal);
	
	// ----- 공지사항 글 삭제 -----
	void deleteNotice(Integer noSeq);
	

	

	

	
}
 
