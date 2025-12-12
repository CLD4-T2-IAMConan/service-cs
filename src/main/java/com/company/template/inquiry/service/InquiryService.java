package com.company.template.inquiry.service;

import com.company.template.inquiry.domain.Inquiry;
import com.company.template.inquiry.domain.InquiryStatus;
import com.company.template.inquiry.dto.InquiryAnswerRequest;
import com.company.template.inquiry.dto.InquiryCreateRequest;
import com.company.template.inquiry.dto.InquiryDetailResponse;
import com.company.template.inquiry.dto.InquiryListItemResponse;
import com.company.template.inquiry.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InquiryService {

    private final InquiryRepository inquiryRepository;

    /**
     * 문의 등록 (사용자)
     * POST /cs/inquiries
     */
    @Transactional
    public InquiryDetailResponse create(Long userId, InquiryCreateRequest request) {
        Inquiry inquiry = Inquiry.builder()
                .userId(userId)
                .type(request.getType())
                .title(request.getTitle())
                .content(request.getContent())
                .imageUrls(request.getImageUrls())
                .status(InquiryStatus.PENDING)
                .build();

        Inquiry saved = inquiryRepository.save(inquiry);
        return InquiryDetailResponse.from(saved);
    }

    /**
     * 내 문의 목록 조회 (사용자)
     * GET /cs/inquiries
     */
    public List<InquiryListItemResponse> getMyInquiries(Long userId) {
        return inquiryRepository.findByUserIdAndDeletedFalseOrderByCreatedAtDesc(userId)
                .stream()
                .map(InquiryListItemResponse::from)
                .toList();
    }

    /**
     * 문의 삭제 (사용자) - 답변 전 문의만 삭제 가능
     * DELETE /cs/inquiries/{id}
     */
    @Transactional
    public void deleteMyInquiry(Long userId, Long inquiryId) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new IllegalArgumentException("문의가 존재하지 않습니다."));

        // 내 문의인지 확인
        if (!inquiry.getUserId().equals(userId)) {
            throw new IllegalStateException("본인 문의만 삭제할 수 있습니다.");
        }

        // 이미 답변완료면 삭제 불가
        if (inquiry.getStatus() == InquiryStatus.ANSWERED) {
            throw new IllegalStateException("답변 완료된 문의는 삭제할 수 없습니다.");
        }

        inquiry.softDelete();
    }

    /**
     * 문의 목록 조회 (관리자)
     * GET /admin/cs/inquiries
     * - status가 있으면 상태별 필터
     * - 없으면 전체
     */
    public List<InquiryListItemResponse> getAllForAdmin(InquiryStatus status) {
        List<Inquiry> inquiries;

        if (status != null) {
            inquiries = inquiryRepository.findByStatusAndDeletedFalseOrderByCreatedAtDesc(status);
        } else {
            inquiries = inquiryRepository.findByDeletedFalseOrderByCreatedAtDesc();
        }

        return inquiries.stream()
                .map(InquiryListItemResponse::from)
                .toList();
    }

    /**
     * 문의 상세 조회 (관리자)
     * GET /admin/cs/inquiries/{id}
     */
    public InquiryDetailResponse getDetailForAdmin(Long inquiryId) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new IllegalArgumentException("문의가 존재하지 않습니다."));
        return InquiryDetailResponse.from(inquiry);
    }

    /**
     * 문의 답변 등록/수정 + 상태 변경 (관리자)
     * PATCH /admin/cs/inquiries/{id}/answer
     */
    @Transactional
    public InquiryDetailResponse answer(Long inquiryId, InquiryAnswerRequest request) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new IllegalArgumentException("문의가 존재하지 않습니다."));

        inquiry.writeAnswer(request.getAnswerContent(), request.getStatus());
        return InquiryDetailResponse.from(inquiry);
    }
}