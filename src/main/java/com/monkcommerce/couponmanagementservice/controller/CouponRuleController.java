package com.monkcommerce.couponmanagementservice.controller;

import com.monkcommerce.couponmanagementservice.dto.CouponRuleRequest;
import com.monkcommerce.couponmanagementservice.dto.CouponRuleResponse;
import com.monkcommerce.couponmanagementservice.service.CouponRuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/coupons/rules")
@RequiredArgsConstructor
public class CouponRuleController {
    private final CouponRuleService ruleService;

    @PostMapping
    public ResponseEntity<CouponRuleResponse> createRule(
            @Valid @RequestBody CouponRuleRequest request) {
        return ResponseEntity.ok(ruleService.createRule(request));
    }

    @GetMapping("/{couponId}")
    public ResponseEntity<List<CouponRuleResponse>> getRules(
            @PathVariable UUID couponId) {
        return ResponseEntity.ok(ruleService.getRulesByCoupon(couponId));
    }

    @PutMapping("/{ruleId}")
    public ResponseEntity<CouponRuleResponse> updateRule(
            @PathVariable UUID ruleId,
            @Valid @RequestBody CouponRuleRequest request) {
        return ResponseEntity.ok(ruleService.updateRule(ruleId, request));
    }

    @DeleteMapping("/{ruleId}")
    public ResponseEntity<Void> deleteRule(@PathVariable UUID ruleId) {
        ruleService.deleteRule(ruleId);
        return ResponseEntity.noContent().build();
    }

}
