package com.company.template.cs.report.repository;

import com.company.template.cs.report.domain.Report;
import com.company.template.cs.report.domain.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    // 사용자 본인이 한 신고 조회
    List<Report> findByUserId(Long userId);

    // 관리자용: 상태별 조회
    List<Report> findByStatus(ReportStatus status);
}