package com.monkcommerce.couponmanagementservice.service;

import com.monkcommerce.couponmanagementservice.dto.CouponRequestDto;
import com.monkcommerce.couponmanagementservice.dto.CouponResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface CouponService {
    CouponResponse createCoupon(CouponRequestDto requestDto);

    CouponResponse getCoupon(UUID id);

    Page<CouponResponse> getAllCoupons(int page, int size);

    CouponResponse updateCoupon(UUID id, CouponRequestDto requestDto);

    void deleteCoupon(UUID id);
}
