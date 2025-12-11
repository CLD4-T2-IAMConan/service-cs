package com.company.template.guide.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class GuideVisibilityRequest {

    @NotNull
    private Boolean visible;
}