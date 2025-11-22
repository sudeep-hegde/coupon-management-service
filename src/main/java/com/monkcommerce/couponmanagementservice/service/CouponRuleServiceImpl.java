package com.monkcommerce.couponmanagementservice.service;

import com.monkcommerce.couponmanagementservice.dto.CouponRuleRequest;
import com.monkcommerce.couponmanagementservice.dto.CouponRuleResponse;
import com.monkcommerce.couponmanagementservice.exception.ResourceAlreadyExistsException;
import com.monkcommerce.couponmanagementservice.exception.ResourceNotFoundException;
import com.monkcommerce.couponmanagementservice.mapper.CouponRuleMapper;
import com.monkcommerce.couponmanagementservice.model.Coupon;
import com.monkcommerce.couponmanagementservice.model.CouponRule;
import com.monkcommerce.couponmanagementservice.repository.CouponRepository;
import com.monkcommerce.couponmanagementservice.repository.CouponRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponRuleServiceImpl implements CouponRuleService {
    private final CouponRuleRepository ruleRepository;
    private final CouponRepository couponRepository;

    @Override
    public CouponRuleResponse createRule(CouponRuleRequest request) {

        Coupon coupon = couponRepository.findById(request.couponId())
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found"));

        if (ruleRepository.existsByCouponIdAndRuleType(request.couponId(), request.ruleType())) {
            throw new ResourceAlreadyExistsException("Rule of type " + request.ruleType() + " already exists for this coupon");
        }


        CouponRule rule = CouponRuleMapper.toEntity(request, coupon);
        ruleRepository.save(rule);

        return CouponRuleMapper.toResponse(rule);
    }

    @Override
    public List<CouponRuleResponse> getRulesByCoupon(UUID couponId) {
        List<CouponRule> rules = ruleRepository.findByCouponIdOrderByPriorityAsc(couponId);
        return rules.stream().map(CouponRuleMapper::toResponse).toList();
    }

    @Override
    public CouponRuleResponse updateRule(UUID ruleId, CouponRuleRequest request) {

        CouponRule rule = ruleRepository.findById(ruleId)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        Coupon coupon = couponRepository.findById(request.couponId())
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found"));

        rule.setCoupon(coupon);
        rule.setRuleType(request.ruleType());
        rule.setRuleConfig(request.ruleConfig());
        rule.setPriority(request.priority());

        ruleRepository.save(rule);

        return CouponRuleMapper.toResponse(rule);
    }

    @Override
    public void deleteRule(UUID ruleId) {
        if (!ruleRepository.existsById(ruleId)) {
            throw new ResourceNotFoundException("Rule not found");
        }
        ruleRepository.deleteById(ruleId);
    }
}
