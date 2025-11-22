package com.monkcommerce.couponmanagementservice.dto;

import java.util.List;

public record ApplicableCouponRequest(
        String userId,
        List<CartItemDto> items
) {
}
