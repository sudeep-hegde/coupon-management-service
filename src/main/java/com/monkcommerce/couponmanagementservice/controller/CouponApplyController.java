package com.monkcommerce.couponmanagementservice.controller;

import com.monkcommerce.couponmanagementservice.dto.ApplyCouponRequest;
import com.monkcommerce.couponmanagementservice.dto.ApplyCouponResponse;
import com.monkcommerce.couponmanagementservice.service.ApplyCoupon;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coupons")
@RequiredArgsConstructor
public class CouponApplyController {
    private final ApplyCoupon applyService;

    @PostMapping("/apply")
    public ResponseEntity<ApplyCouponResponse> applyCoupon(
            @RequestBody ApplyCouponRequest request
    ) {
        return ResponseEntity.ok(applyService.applyCoupon(request));
    }
}
