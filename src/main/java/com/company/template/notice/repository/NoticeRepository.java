package com.company.template.notice.repository;

import com.company.template.notice.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    // 사용자용: 노출된 공지만 가져오기
    Page<Notice> findByVisibleTrue(Pageable pageable);
}