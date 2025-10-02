package com.library.entity;

public enum MemberType {
    REGULAR("일반회원", 3, 14, 1, 1),
    SILVER("실버회원", 5, 21, 2, 2),
    GOLD("골드회원", 7, 28, 3, 3),
    VIP("VIP회원", 10, 30, 5, Integer.MAX_VALUE);

    private final String description;
    private final int maxRentalBooks;
    private final int rentalPeriodDays;
    private final int maxReservationBooks;
    private final int maxRenewalCount;

    MemberType(String description, int maxRentalBooks, int rentalPeriodDays, int maxReservationBooks, int maxRenewalCount) {
        this.description = description;
        this.maxRentalBooks = maxRentalBooks;
        this.rentalPeriodDays = rentalPeriodDays;
        this.maxReservationBooks = maxReservationBooks;
        this.maxRenewalCount = maxRenewalCount;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxRentalBooks() {
        return maxRentalBooks;
    }

    // 등급 레벨 반환
    public int getLevel() {
        switch (this) {
            case REGULAR: return 1;
            case SILVER: return 2;
            case GOLD: return 3;
            case VIP: return 4;
            default: return 0;
        }
    }
}















