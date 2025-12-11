package com.company.template.guide.dto;

import com.company.template.guide.domain.Guide;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class GuideListItemResponse {

    private Long id;
    private String title;
    private boolean visible;
    private int sortOrder;
    private LocalDateTime createdAt;

    public static GuideListItemResponse from(Guide guide) {
        return GuideListItemResponse.builder()
                .id(guide.getId())
                .title(guide.getTitle())
                .visible(guide.isVisible())
                .sortOrder(guide.getSortOrder())
                .createdAt(guide.getCreatedAt())
                .build();
    }
}