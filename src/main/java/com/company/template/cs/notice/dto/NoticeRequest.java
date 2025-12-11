package com.company.template.cs.notice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeRequest {

    private String title;
    private String content;
    private Long categoryId;

    // 관리자 화면에서 노출/고정 상태까지 함께 설정할 수 있게
    private Boolean visible;
    private Boolean pinned;
}