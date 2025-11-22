package com.monkcommerce.couponmanagementservice.dto;

import java.util.List;
import java.util.UUID;

public record ApplicableCouponDto(
        UUID couponId,
        String code,
        double discount,
        String message,
        String stackingType,
        List<FreeItem> freeItems
) {}