package com.monkcommerce.couponmanagementservice.service;

import com.monkcommerce.couponmanagementservice.dto.ApplicableCouponDto;
import com.monkcommerce.couponmanagementservice.dto.ApplicableCouponRequest;
import com.monkcommerce.couponmanagementservice.dto.ApplicableCouponResponse;
import com.monkcommerce.couponmanagementservice.exception.CouponValidationException;
import com.monkcommerce.couponmanagementservice.model.Coupon;
import com.monkcommerce.couponmanagementservice.model.CouponRule;
import com.monkcommerce.couponmanagementservice.repository.CouponRepository;
import com.monkcommerce.couponmanagementservice.repository.CouponRuleRepository;
import com.monkcommerce.couponmanagementservice.repository.UsageRepository;
import com.monkcommerce.couponmanagementservice.rule.RuleEngine;
import com.monkcommerce.couponmanagementservice.rule.RuleEvaluationContext;
import com.monkcommerce.couponmanagementservice.rule.RuleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicableCouponServiceImpl implements ApplicableCouponService {

    private final CouponRepository couponRepository;
    private final CouponRuleRepository ruleRepository;
    private final UsageRepository usageRepository;
    private final RuleEngine ruleEngine;
    private final CouponValidator couponValidator;

    @Override
    public ApplicableCouponResponse getApplicableCoupons(ApplicableCouponRequest req) {
        List<Coupon> coupons = couponRepository.findByActiveTrue();

        List<ApplicableCouponDto> results = new ArrayList<>();

        for (Coupon coupon : coupons) {
            try {
                couponValidator.validate(coupon, req.userId());
            } catch (CouponValidationException ex) {
                continue;
            }
            List<CouponRule> rules = ruleRepository.findByCouponIdOrderByPriorityAsc(coupon.getId());

            RuleEvaluationContext ctx = new RuleEvaluationContext(
                    coupon,
                    rules,
                    req.items(),
                    req.userId()
            );

            RuleResult result = ruleEngine.evaluateRules(ctx);

            if (!result.valid()) {
                continue; // skip
            }
            ApplicableCouponDto dto = new ApplicableCouponDto(
                    coupon.getId(),
                    coupon.getCode(),
                    result.discount(),
                    result.message(),
                    coupon.getStackingType().name(),
                    result.freeItems()
            );
            results.add(dto);
        }
        results.sort(Comparator.comparingDouble(ApplicableCouponDto::discount).reversed());

        return new ApplicableCouponResponse(results);
    }
}
