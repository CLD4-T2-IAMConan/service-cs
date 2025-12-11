package com.company.template.cs.report.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportRequest {

    private Long userId;
    private String targetType;
    private Long targetId;
    private String reason;
}