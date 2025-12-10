package com.company.template.cs.report.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportStatusUpdateRequest {
    private String status; // "PENDING" or "PROCESSED"
}