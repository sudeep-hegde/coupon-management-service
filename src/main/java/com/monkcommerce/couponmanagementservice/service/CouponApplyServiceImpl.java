package com.monkcommerce.couponmanagementservice.service;

import com.monkcommerce.couponmanagementservice.dto.ApplyCouponRequest;
import com.monkcommerce.couponmanagementservice.dto.ApplyCouponResponse;
import com.monkcommerce.couponmanagementservice.exception.CouponValidationException;
import com.monkcommerce.couponmanagementservice.exception.ResourceNotFoundException;
import com.monkcommerce.couponmanagementservice.model.Coupon;
import com.monkcommerce.couponmanagementservice.model.CouponRule;
import com.monkcommerce.couponmanagementservice.model.CouponUsage;
import com.monkcommerce.couponmanagementservice.repository.CouponRepository;
import com.monkcommerce.couponmanagementservice.repository.CouponRuleRepository;
import com.monkcommerce.couponmanagementservice.repository.UsageRepository;
import com.monkcommerce.couponmanagementservice.rule.RuleEngine;
import com.monkcommerce.couponmanagementservice.rule.RuleEvaluationContext;
import com.monkcommerce.couponmanagementservice.rule.RuleResult;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CouponApplyServiceImpl implements ApplyCoupon {

    private final CouponRepository repository;
    private final RuleEngine ruleEngine;
    private final CouponRuleRepository ruleRepository;
    private final CouponValidator couponValidator;
    private final UsageRepository usageRepository;

    @Override
    @Transactional
    public ApplyCouponResponse applyCoupon(ApplyCouponRequest request) {
        Coupon coupon = repository.findByCode(request.couponCode())
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found"));

        couponValidator.validate(coupon, request);

        List<CouponRule> rules = ruleRepository.findByCouponIdOrderByPriorityAsc(coupon.getId());


        RuleEvaluationContext context = new RuleEvaluationContext(
                coupon,
                rules,
                request.items(),
                request.userId()
        );
        RuleResult result = ruleEngine.evaluateRules(context);
        if (!result.valid()) {
            return new ApplyCouponResponse(false, 0, 0, result.message(), result.freeItems());
        }
        double total = request.items().stream()
                .mapToDouble(i -> i.price() * i.quantity())
                .sum();
        double finalPrice = total - result.discount();
        // concurrent coupon-apply calls.
        int rows = repository.incrementUsage(coupon.getId());
        if (rows == 0) {
            throw new CouponValidationException("Coupon usage limit reached");
        }

        saveUsageRecord(coupon, request, result.discount());
        return new ApplyCouponResponse(true, result.discount(), finalPrice, result.message(),result.freeItems());
    }

    private void saveUsageRecord(Coupon coupon, ApplyCouponRequest req, double discount) {
        CouponUsage usage = CouponUsage.builder()
                .coupon(coupon)
                .userId(req.userId())
                .cartId(req.cartId())
                .discountApplied(BigDecimal.valueOf(discount))
                .cartSnapshot(Map.of(
                        "items", req.items()
                ))
                .build();

        usageRepository.save(usage);
    }
}
