package com.library.entity.member;

public enum MemberStatus {
    ACTIVE("활성"),
    SUSPENDED("정지"),
    WITHDRAWN("탈퇴");

    private final String description;

    MemberStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    // 상태 변경 가능 여부 확인
    public boolean canTransitionTo(MemberStatus targetStatus) {
        switch (this) {
            case ACTIVE:
                // 활성 -> 정지/탈퇴 가능
                return targetStatus == SUSPENDED || targetStatus == WITHDRAWN;
            case SUSPENDED:
                // 정지 -> 활성/탈퇴 가능
                return targetStatus == ACTIVE || targetStatus == WITHDRAWN;
            case WITHDRAWN:
                // 탈퇴 -> 변경 불가 (영구)
                return false;
            default:
                return false;
        }
    }
}
