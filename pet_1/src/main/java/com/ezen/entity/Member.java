package com.ezen.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;


import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Member")
@Data
@NoArgsConstructor //디폴트생성자
@AllArgsConstructor //모든 필드가 있는 생성자
public class Member {
	
		// ID = PK 키
		@Id
		@Column(name = "member_id")
		private String memberId; // 멤버 id
			
		//	true = NULL false = NOT NULL
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
			
		@Column(name = "member_image")
		private String memberImage; // 자격증 이미지
			
		@Column(name = "member_path")
		private String memberPath; // 이미지 이름
			
		@Column(name = "member_type", nullable = true, columnDefinition="char(1) default 4")
		private char memberType; // 사용자 타입

		   /*
		    * 
		    * //사용자 타입(멤버, 시터, 훈련사)
		    * 
		    * @Enumerated(EnumType.STRING)
		    * 
		    * @Column(name="member_type", columnDefinition =
		    * "varchar(20) default 'ROLE_MEMBER'") private Role role;
		    * 
		    * 쌤이 해주신 부분..
		    */
			
		//@Enumerated(EnumType.STRING)
		@Column(nullable = false)
		private String Role;  // ROLE_USER, ROLE_ADMIN
		

		
		@Builder
		  public Member(String memberId, String memberPw, String memberName, String memberEmail, String memberPhone, String memberAddress, String memberTitle, String memberIy, String memberImage, char memberType, String memberPath, String Role) {
			this.memberId = memberId;
			this.memberPw = memberPw;
			this.memberName = memberName;
			this.memberEmail = memberEmail;
			this.memberPhone =  memberPhone;
			this.memberAddress = memberAddress;
			this.memberTitle = memberTitle;
			this.memberIy = memberIy;
			this.memberImage = memberImage;
			this.memberType = memberType;
			this.memberPath = memberPath;
			this.Role = Role;
}
}
    
//    // DTO 값을 옮겨 담는다
//    public static MemberEntity toSaveEntity(MemberDTO memberDTO) {
//    	MemberEntity memberEntity = new MemberEntity();
//    	memberEntity.setMember_email(memberDTO.getMember_email()); 
//    	memberEntity.setMember_pw(memberDTO.getMember_pw());
//    	memberEntity.setMember_name(memberDTO.getMember_name());
//    	memberEntity.setMember_phone(memberDTO.getMember_phone());
//    	memberEntity.setMember_address(memberDTO.getMember_address());
//    	memberEntity.setMember_title(memberDTO.getMember_title());
//    	memberEntity.setMember_iy(memberDTO.getMember_iy());
//    	memberEntity.setMember_Image(memberDTO.getMember_Image());
//    	memberEntity.setMember_type(memberDTO.getMember_type());
//    	
//    	return memberEntity;
//    }

