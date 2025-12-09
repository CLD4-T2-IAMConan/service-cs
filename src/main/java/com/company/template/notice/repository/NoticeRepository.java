package com.company.template.notice.repository;

import com.company.template.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    // 유저용: visible = true 인 공지만
    List<Notice> findByVisibleTrueOrderByPinnedDescCreatedAtDesc();
}