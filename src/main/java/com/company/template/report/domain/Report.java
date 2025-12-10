package com.company.template.cs.report.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "cs_report")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 신고한 사용자 ID
    @Column(name = "user_id", nullable = false)
    private Long userId;

    // 어떤 대상에 대한 신고인지 (예: "TICKET", "USER", "POST" 등 문자열로 관리)
    @Column(name = "target_type", nullable = false, length = 50)
    private String targetType;

    // 신고 대상의 식별자 (예: 티켓 ID, 유저 ID 등)
    @Column(name = "target_id", nullable = false)
    private Long targetId;

    // 신고 사유
    @Column(name = "reason", nullable = false, length = 255)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ReportStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // 신고 생성용 생성자
    public Report(Long userId, String targetType, Long targetId, String reason) {
        this.userId = userId;
        this.targetType = targetType;
        this.targetId = targetId;
        this.reason = reason;
        this.status = ReportStatus.PENDING;
    }

    // 상태 변경 도메인 메서드
    public void updateStatus(ReportStatus status) {
        this.status = status;
    }

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = ReportStatus.PENDING;
        }
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}