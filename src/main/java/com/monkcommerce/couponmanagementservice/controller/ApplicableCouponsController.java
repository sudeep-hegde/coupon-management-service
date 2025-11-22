package com.monkcommerce.couponmanagementservice.controller;

import com.monkcommerce.couponmanagementservice.dto.ApplicableCouponRequest;
import com.monkcommerce.couponmanagementservice.dto.ApplicableCouponResponse;
import com.monkcommerce.couponmanagementservice.service.ApplicableCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coupons")
@RequiredArgsConstructor
public class ApplicableCouponsController {

    private final ApplicableCouponService applicableCouponService;

    @PostMapping("/applicable")
    public ResponseEntity<ApplicableCouponResponse> getApplicableCoupons(
            @RequestBody ApplicableCouponRequest request
    ) {
        return ResponseEntity.ok(applicableCouponService.getApplicableCoupons(request));
    }
}
