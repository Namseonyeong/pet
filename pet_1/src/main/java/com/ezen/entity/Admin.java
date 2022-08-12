package com.ezen.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "Admin")
@Entity
@Data
public class Admin {

		// 관리자
	   @Id
	   @Column(name = "admin_id", nullable = false)
	   private String adminId;

	   // 관리자PW
	   @Column(name = "admin_pw", nullable = false)
	   private String adminPw;

	   // 관리자 이름
	   @Column(name = "admin_name", nullable = false)
	   private String adminName;

	   // 관리자 이메일
	   @Column(name = "admin_email", nullable = false)
	   private String adminEmail;

	}
        
