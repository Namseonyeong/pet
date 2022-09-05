// 채완 

package com.ezen.notice.service;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ezen.Repository.NoticeRepository;
import com.ezen.entity.Notice;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeRepository noRepo;



	// ----- 공지사항 게시글 전체 조회 -----
	@Override
	public Page<Notice> noticeList(Notice notice, Pageable pageable) {
		
		return noRepo.findAll(pageable);
	}

	// ----- 공지사항 글 상세 조회 -----
	@Override
	public Notice noticeView(Integer noSeq) {
		
		return noRepo.findById(noSeq).get();
	}
	
	// ----- 마이페이지 '본인' 작성글 조회 -----
//	public Page<CustomerService> findCustomerListByMemberId(String memberId, Pageable pageable) {
		
//		return (Page<CustomerService>)csRepo.findCustomerListByMemberId(memberId, pageable);
//	}

	// ----- 공지사항 글 등록 -----
	@Override
	public void insertNotice(Notice notice) {
		
		noRepo.save(notice);
	}

	// ----- 공지사항 글 수정 -----
	@Override
	public void updateNotice(Notice notice, Principal principal) {
		
		Notice findNotice = noRepo.findById(notice.getNoSeq()).get();
		
		findNotice.setNoTitle(notice.getNoTitle());
		findNotice.setNoContent(notice.getNoContent());
		findNotice.setNoRegDate(notice.getNoRegDate());
		
		noRepo.save(findNotice);
	}

	// ----- 공지사항 글 삭제 -----
	@Override
	public void deleteNotice(Integer noSeq) {
		
		noRepo.deleteById(noSeq);
	}

}


