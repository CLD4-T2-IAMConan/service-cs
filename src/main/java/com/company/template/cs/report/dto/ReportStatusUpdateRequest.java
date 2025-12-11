package com.company.template.cs.report.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportStatusUpdateRequest {

    // "PENDING" 또는 "PROCESSED"
    private String status;
}