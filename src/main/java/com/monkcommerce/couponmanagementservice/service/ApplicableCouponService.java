package com.monkcommerce.couponmanagementservice.service;

import com.monkcommerce.couponmanagementservice.dto.ApplicableCouponRequest;
import com.monkcommerce.couponmanagementservice.dto.ApplicableCouponResponse;

public interface ApplicableCouponService {
    ApplicableCouponResponse getApplicableCoupons(ApplicableCouponRequest request);
}
