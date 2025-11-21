package com.monkcommerce.couponmanagementservice.service;

import com.monkcommerce.couponmanagementservice.dto.ApplyCouponRequest;
import com.monkcommerce.couponmanagementservice.dto.ApplyCouponResponse;

public interface ApplyCoupon {
    ApplyCouponResponse applyCoupon(ApplyCouponRequest request);
}
