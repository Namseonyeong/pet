package com.ezen.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ezen.security.test.Role;

import lombok.Data;

//UserDetails는 시큐리티가 관리하는 객체이다.
@Entity
@Table(name = "Member")
@Data
// @NoArgsConstructor //디폴트생성자
// @AllArgsConstructor //모든 필드가 있는 생성자
public class Member {

	// ID = PK 키
	@Id
	@Column(name = "member_id")
	private String memberId; // 멤버 id

	// true = NULL false = NOT NULL
	@Column(name = "member_pw", nullable = false)
	private String memberPw;

	@Column(name = "member_name", nullable = false)
	private String memberName;

	@Column(name = "member_email", nullable = false)
	private String memberEmail;

	@Column(name = "member_phone", nullable = false)
	private String memberPhone;

	@Column(name = "member_address", nullable = false)
	private String memberAddress;

	@Column(name = "member_title")
	private String memberTitle; // 프로필 제목

	@Column(name = "member_iy", length = 2000)
	private String memberIy; // 자기소개

	@Column(name = "member_image", length = 2000)
	private String memberImage; // 자격증 이미지

	@Column(name = "member_path")
	private String memberPath; // 이미지 이름

	@Column(name = "member_type", nullable = true, columnDefinition = "char(1) default 'D'")
	private String memberType; // 사용자 타입

	@Column(name = "member_status", nullable = true, columnDefinition = "char(1) default 'N'")
	private String memberStatus; // 펫시터, 훈련사 승인 여부

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(20) default 'ROLE_MEMBER'")
	private Role role; // ROLE_USER, ROLE_ADMIN

	// @Builder
	// public Member(String memberId, String memberPw, String memberName, String
	// memberEmail, String memberPhone, String memberAddress, String memberTitle,
	// String memberIy, String memberImage, String memberPath, String memberType,
	// String memberStatus, Role role) {
	// this.memberId = memberId;
	// this.memberPw = memberPw;
	// this.memberName = memberName;
	// this.memberEmail = memberEmail;
	// this.memberPhone = memberPhone;
	// this.memberAddress = memberAddress;
	// this.memberTitle = memberTitle;
	// this.memberIy = memberIy;
	// this.memberImage = memberImage;
	// this.memberPath = memberPath;
	// this.memberType = memberType;
	// this.memberStatus = memberStatus;
	// this.role = role;

	// }

}
