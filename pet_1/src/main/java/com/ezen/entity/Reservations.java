package com.ezen.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;


@Table(name = "Reservations")
@Entity
@Data
public class Reservations {

	// 펫시터/훈련사 예약
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "rs_seq")
	private Integer rsSeq;

	// 예약 날짜
	@Column(name = "rs_date", nullable = false)
	private String rsDate;
	
	// 예약 시간
	@Column(name = "rs_time", nullable = false)
	private String rsTime;
	
	// 교육 선택
	@Column(name = "rs_edu")
	private String rsEdu;
		   
	// 총 이용시간
	@Column(name = "rs_time2")
	private String rsTime2;
		
	// 견종
	@Column(name = "rs_kind")
	private String rsKind;
		
	// 지역
	@Column(name = "rs_area", nullable = false)
	private String rsArea;
		 
	//예약승인여부
	@Column(name = "rs_status", columnDefinition="char(1) default 'N'")
	private String rsStatus;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	// private String memberId;
		
			
}

