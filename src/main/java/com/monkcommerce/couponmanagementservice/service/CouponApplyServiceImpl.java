package com.monkcommerce.couponmanagementservice.service;

import com.monkcommerce.couponmanagementservice.dto.ApplyCouponRequest;
import com.monkcommerce.couponmanagementservice.dto.ApplyCouponResponse;
import com.monkcommerce.couponmanagementservice.model.Coupon;
import com.monkcommerce.couponmanagementservice.model.CouponRule;
import com.monkcommerce.couponmanagementservice.repository.CouponRepository;
import com.monkcommerce.couponmanagementservice.repository.CouponRuleRepository;
import com.monkcommerce.couponmanagementservice.rule.RuleEngine;
import com.monkcommerce.couponmanagementservice.rule.RuleEvaluationContext;
import com.monkcommerce.couponmanagementservice.rule.RuleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponApplyServiceImpl implements ApplyCoupon{

    private final CouponRepository repository;
    private final RuleEngine ruleEngine;
    private final CouponRuleRepository ruleRepository;

    @Override
    public ApplyCouponResponse applyCoupon(ApplyCouponRequest request) {
        Coupon coupon = repository.findByCode(request.couponCode())
                .orElseThrow(() -> new RuntimeException("Coupon not found"));
        List<CouponRule> rules = ruleRepository.findByCouponIdOrderByPriorityAsc(coupon.getId());


        RuleEvaluationContext context = new RuleEvaluationContext(
                coupon,
                rules,
                request.items(),
                request.userId()
        );
        RuleResult result = ruleEngine.evaluateRules(context);
        if (!result.valid()) {
            return new ApplyCouponResponse(false, 0, 0, result.message());
        }
        double total = request.items().stream()
                .mapToDouble(i -> i.price() * i.quantity())
                .sum();
        double finalPrice = total - result.discount();
        return new ApplyCouponResponse(true, result.discount(), finalPrice, result.message());
    }
}
