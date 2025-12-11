package com.company.template.guide.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class GuideRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Boolean visible;

    @NotNull
    private Integer sortOrder;
}