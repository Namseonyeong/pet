package com.ezen.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


//import com.ezen.domain.security.Role;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "member")
@Data
public class MemberEntity {
	
	// ID = PK 키
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "member_id")
	private Long id; // 멤버 id
	
//	true = 널 false = 낫널
	@Column(nullable = false, length = 50, unique = true)
	private String member_email;
	
	@Column(nullable = false, length = 20)
	private String member_pw;
	
	@Column(nullable = false, length = 40)
	private String member_name;
	
	@Column(nullable = false, length = 30)
	private String member_phone;
	
	
	// 
	@Column(nullable = false, length = 100)
	private String member_address;
	
	@Column(nullable = true, length = 40)
	private String member_title; // 프로필 제목
	
	@Column(nullable = true, length = 2000)
	private String member_iy; // 자기소개
	
	@Column(nullable = true, length = 100)
	private String member_Image; // 자격증 이미지
	
	@Column(nullable = true)
	private char member_type; // 사용자 타입
	
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Role role;
//    
    
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
}
