package com.monkcommerce.couponmanagementservice.service;

import com.monkcommerce.couponmanagementservice.dto.ApplyCouponRequest;
import com.monkcommerce.couponmanagementservice.exception.CouponValidationException;
import com.monkcommerce.couponmanagementservice.model.Coupon;
import com.monkcommerce.couponmanagementservice.repository.UsageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CouponValidator {
    private final UsageRepository usageRepository;

    public void validate(Coupon coupon, String userId) {
        checkActive(coupon);
        checkDateValidity(coupon);
        checkGlobalUsage(coupon);
        checkPerUserUsage(coupon, userId);
    }

    public void validate(Coupon coupon, ApplyCouponRequest req) {
        checkActive(coupon);
        checkDateValidity(coupon);
        checkGlobalUsage(coupon);
        checkPerUserUsage(coupon, req.userId());
        checkCartUsage(coupon, req.cartId());
    }

    private void checkActive(Coupon coupon) {
        if (!coupon.getActive()) {
            throw new CouponValidationException("Coupon is inactive");
        }
    }

    private void checkDateValidity(Coupon coupon) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(coupon.getStartDate()) || now.isAfter(coupon.getEndDate())) {
            throw new CouponValidationException("Coupon expired or not valid yet");
        }
    }

    private void checkGlobalUsage(Coupon coupon) {
//        int totalUsed = usageRepository.countByCouponId(coupon.getId());
        if (coupon.getUsedCount() >= coupon.getMaxUses()) {
            throw new CouponValidationException("Coupon usage limit exceeded");
        }
    }

    private void checkPerUserUsage(Coupon coupon, String userId) {
        int used = usageRepository.countByCouponIdAndUserId(coupon.getId(), userId);
        if (used >= coupon.getPerUserLimit()) {
            throw new CouponValidationException("User has exceeded coupon usage limit");
        }
    }

    private void checkCartUsage(Coupon coupon, String cartId) {
        if (usageRepository.existsByCouponIdAndCartId(coupon.getId(), cartId)) {
            throw new CouponValidationException("Coupon already applied on this cart");
        }
    }
}
