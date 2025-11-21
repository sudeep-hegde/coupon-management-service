package com.monkcommerce.couponmanagementservice.dto;

import java.util.List;

public record ApplyCouponRequest(
        String couponCode,
        String userId,
        List<CartItemDto> items
) {
}
