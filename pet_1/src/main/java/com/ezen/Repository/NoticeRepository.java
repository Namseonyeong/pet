// 채완 

package com.ezen.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ezen.entity.Notice;


@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
// 이 레파지토리를 통해서 어떤 엔티티 클래스를 다룰것이냐, 그 해당 엔티티 클래스의 pk(Long 등) 타입은 무엇이냐

	// ----- 공지사항 게시글 전체 조회. -----
	@Query("SELECT n FROM Notice n ORDER BY n.noSeq")
	Page<Notice> findAll(Pageable pageable);
	

	
}