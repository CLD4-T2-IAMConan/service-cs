package com.company.template.cs.report.controller;

import com.company.template.cs.report.dto.ReportRequest;
import com.company.template.cs.report.dto.ReportResponse;
import com.company.template.cs.report.dto.ReportStatusUpdateRequest;
import com.company.template.cs.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cs")
public class ReportController {

    private final ReportService reportService;

    // 사용자 신고 생성
    @PostMapping("/reports")
    public ReportResponse createReport(@RequestBody ReportRequest request) {
        return reportService.create(request);
    }

    // 사용자 본인 신고 목록 조회
    @GetMapping("/reports/{userId}")
    public List<ReportResponse> getReportsForUser(@PathVariable Long userId) {
        return reportService.getReportsForUser(userId);
    }

    // 관리자 신고 전체 조회
    @GetMapping("/admin/reports")
    public List<ReportResponse> getReportsForAdmin() {
        return reportService.getReportsForAdmin();
    }

    // 신고 상태 변경 (관리자)
    @PatchMapping("/admin/reports/{id}/status")
    public ReportResponse updateStatus(
            @PathVariable Long id,
            @RequestBody ReportStatusUpdateRequest request
    ) {
        return reportService.updateStatus(id, request);
    }

    // 신고 삭제 (관리자)
    @DeleteMapping("/admin/reports/{id}")
    public void delete(@PathVariable Long id) {
        reportService.delete(id);
    }
}