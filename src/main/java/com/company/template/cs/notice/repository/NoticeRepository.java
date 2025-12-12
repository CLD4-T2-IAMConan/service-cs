package com.company.template.cs.notice.repository;

import com.company.template.cs.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    // 유저용: visible = true 인 공지만
    List<Notice> findByVisibleTrueOrderByPinnedDescCreatedAtDesc();
}