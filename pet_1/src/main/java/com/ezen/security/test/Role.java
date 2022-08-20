package com.ezen.security.test;
// 선영 : 회원가입 Role 권한설정

//@AllArgsConstructor
//@Getter
//public enum Role {
//	ADMIN("ROLE_ADMIN"), MEMBER("ROLE_MEMBER");
//
//	private String value;
//}

public enum Role {
	ROLE_ADMIN, ROLE_MEMBER;
}

// 회원가입 진행할때 있는 정보 (뭔지 물어보기)
//private String description;
//
//Role(String description) {
//    this.description = description;
//}
//
//
////public enum Role {
////	 MEMBER("ROLE_MEMBER"),
////	 ADMIN ("ROLE_ADMIN");
////	
////	private final String value;
//
//
////ROLE_ADMIN, ROLE_MANAGER, ROLE_MEMBER
////}
//
//
//
////
////alter table user 
////change column role 
////role enum('ROLE_USER','ROLE_ADMIN');
////
////update user set role="ROLE_USER";
////
////update user set role="ROLE_ADMIN" where name = "관리자";