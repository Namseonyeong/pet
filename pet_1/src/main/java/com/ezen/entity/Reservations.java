// 채완
package com.ezen.entity;

import javax.persistence.CascadeType;
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
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "member_id")
	private Member member;
	// private String memberId;
		

/*	public void setReservationsStatus() {
	    if() {
	        this.rsStatus = 
	      } else {
	        this.rsStatus = 
	      }
	    }
*/
/*	
	 public static Reservations createReservation(String rsDate, String rsTime, String rsEdu, String rsTime2, String rsKind, String rsArea, String rsStatus, Member member) {
		 
		Reservations reservation = new Reservations();
		 
		reservation.setRsDate(rsDate);
		reservation.setRsTime(rsTime);
		reservation.setRsEdu(rsEdu);
		reservation.setRsTime2(rsTime2);
		reservation.setRsKind(rsKind);
		reservation.setRsArea(rsArea);
		//reservation.setRsStatus(rsStatus);
		reservation.setMember(member);
		
		return reservation;
	}
*/
	/*
	@Builder (builderMethodName = "createReservation")
	public Reservations (String rsDate, String rsTime, String rsEdu, String rsTime2, String rsKind, String rsArea, String rsStatus, Member member) {
		this.rsDate = rsDate;
		this.rsTime = rsTime;
		this.rsEdu = rsEdu;
		this.rsTime2 = rsTime2;
		this.rsKind = rsKind;
		this.rsArea = rsArea;
		this.rsStatus = rsStatus;
		this.member = member;
	}*/
}


			


