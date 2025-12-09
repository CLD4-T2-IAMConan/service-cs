package com.company.template.notice.service;

import com.company.template.notice.domain.Notice;
import com.company.template.notice.dto.NoticeRequest;
import com.company.template.notice.dto.NoticeResponse;
import com.company.template.notice.dto.NoticeStatusRequest;
import com.company.template.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeService {

    private final NoticeRepository noticeRepository;

    // 관리자: 공지 생성
    public NoticeResponse create(NoticeRequest request) {
        Notice notice = Notice.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .categoryId(request.getCategoryId())
                .visible(true)
                .pinned(false)
                .build();

        return NoticeResponse.from(noticeRepository.save(notice));
    }

    // 단건 조회 (공통)
    @Transactional(readOnly = true)
    public NoticeResponse getById(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("공지사항을 찾을 수 없습니다."));
        return NoticeResponse.from(notice);
    }

    // 사용자용 목록 (노출된 공지만)
    @Transactional(readOnly = true)
    public Page<NoticeResponse> getVisibleList(Pageable pageable) {
        return noticeRepository.findByVisibleTrue(pageable)
                .map(NoticeResponse::from);
    }

    // 관리자용 전체 목록
    @Transactional(readOnly = true)
    public Page<NoticeResponse> getAdminList(Pageable pageable) {
        return noticeRepository.findAll(pageable)
                .map(NoticeResponse::from);
    }

    // 공지 수정
    public NoticeResponse update(Long id, NoticeRequest request) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("공지사항을 찾을 수 없습니다."));

        notice.update(request.getTitle(), request.getContent(), request.getCategoryId());
        return NoticeResponse.from(notice);
    }

    // 상태(visible/pinned) 변경
    public NoticeResponse updateStatus(Long id, NoticeStatusRequest request) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("공지사항을 찾을 수 없습니다."));

        notice.updateStatus(request.getVisible(), request.getPinned());
        return NoticeResponse.from(notice);
    }

    // 삭제
    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }
}