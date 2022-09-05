package com.ezen.entity;
  
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data; 
  
@Table(name = "Customer_Service")
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class CustomerService {
  
	// 고객센터 
 
	  @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE)
	  @Column(name = "cs_seq")
	  private Integer csSeq;
	  
	  // 제목
	  @Column(name = "cs_title", nullable = false) 
	  private String csTitle;
	  
	  // 내용
	  @Column(name = "cs_content", nullable = false) 
	  private String csContent;
	  
	  // 등록일
	  @Column(name = "cs_regDate")
	  @CreatedDate
	  private LocalDateTime csRegDate;
	  
	  // 답변내용
//	  @Column(name = "cs_replycontent", length = 1000)
//	  private String csReplyContent;

	  // 답변 작성일
//	  @Column(name = "cs_replyregDate")
//	  private LocalDateTime csReplyRegDate;

	  // 답변 유무
//	  @Column(name = "cs_yn", columnDefinition = "char(1) default 'N'")
//	  private String csYn;
	  
	  @ManyToOne
	  @JoinColumn(name = "member_id")
	  private Member member;

	  @ManyToOne
	  @JoinColumn(name = "admin_id")
	  private Admin admin;
	  
	  // 조회수
	  @Column(name = "cs_cnt" , columnDefinition = "Integer default 0")
	  private Long csCnt;
		  
  }
 

