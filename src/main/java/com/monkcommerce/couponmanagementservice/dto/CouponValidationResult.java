package com.monkcommerce.couponmanagementservice.dto;

public record CouponValidationResult(boolean valid,
                                     String message) {
}
