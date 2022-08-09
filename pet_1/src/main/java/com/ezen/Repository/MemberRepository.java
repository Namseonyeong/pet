package com.ezen.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezen.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{
// 이 레파지토리를 통해서 어떤 엔티티 클래스를 다룰것이냐, 그 해당 엔티티 클래스의 pk(Long 등) 타입은 무엇이냐
	
	
}



