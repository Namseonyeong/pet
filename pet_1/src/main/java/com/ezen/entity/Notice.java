package com.ezen.entity;
  
import java.time.LocalDateTime;
import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
  
import lombok.Data;
  
@Entity
@Table(name = "Notice")
@Data 
public class Notice {
  
		// 공지사항
		  
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		@Column(name="no_seq") 
		private Integer noSeq;
		  
		//제목
		@Column(name="no_title") 
		private String noTitle;
		  
		//내용
		@Column(name="no_content", length=1000)
		private String noContent;
		  
		//작성일
		@Column(name="noregDate") 
		private LocalDateTime noRegDate;

}
 