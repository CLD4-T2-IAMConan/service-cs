package com.company.template.notice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeStatusRequest {

    private Boolean visible;
    private Boolean pinned;
}