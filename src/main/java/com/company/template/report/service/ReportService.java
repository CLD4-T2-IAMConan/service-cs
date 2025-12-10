package com.company.template.cs.report.service;

import com.company.template.cs.report.domain.Report;
import com.company.template.cs.report.domain.ReportStatus;
import com.company.template.cs.report.dto.ReportRequest;
import com.company.template.cs.report.dto.ReportResponse;
import com.company.template.cs.report.dto.ReportStatusUpdateRequest;
import com.company.template.cs.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    @Transactional
    public ReportResponse create(ReportRequest request) {

        Report report = new Report(
                request.getUserId(),
                request.getTargetType(),
                request.getTargetId(),
                request.getReason()
        );

        return ReportResponse.from(reportRepository.save(report));
    }

    @Transactional(readOnly = true)
    public List<ReportResponse> getReportsForUser(Long userId) {
        return reportRepository.findByUserId(userId).stream()
                .map(ReportResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ReportResponse> getReportsForAdmin() {
        return reportRepository.findAll().stream()
                .map(ReportResponse::from)
                .toList();
    }

    @Transactional
    public ReportResponse updateStatus(Long id, ReportStatusUpdateRequest request) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("신고 내역 없음"));

        ReportStatus status = ReportStatus.valueOf(request.getStatus());
        report.updateStatus(status);

        return ReportResponse.from(report);
    }

    @Transactional
    public void delete(Long id) {
        reportRepository.deleteById(id);
    }
}