package com.company.template.notice.dto;

import com.company.template.notice.domain.Notice;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NoticeResponse {

    private Long id;
    private String title;
    private String content;
    private Long categoryId;
    private Boolean visible;
    private Boolean pinned;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static NoticeResponse from(Notice notice) {
        return NoticeResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .categoryId(notice.getCategoryId())
                .visible(notice.getVisible())
                .pinned(notice.getPinned())
                .createdAt(notice.getCreatedAt())
                .updatedAt(notice.getUpdatedAt())
                .build();
    }
}