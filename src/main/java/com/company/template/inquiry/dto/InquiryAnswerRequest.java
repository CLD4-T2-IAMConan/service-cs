package com.company.template.inquiry.dto;

import com.company.template.inquiry.domain.InquiryStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquiryAnswerRequest {

    @NotBlank
    private String answerContent;   // 답변 내용

    @NotNull
    private InquiryStatus status;   // ANSWERED / PENDING
}