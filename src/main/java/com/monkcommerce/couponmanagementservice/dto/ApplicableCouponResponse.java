package com.monkcommerce.couponmanagementservice.dto;

import java.util.List;

public record ApplicableCouponResponse(
        List<ApplicableCouponDto> applicableCoupons
) {
}
