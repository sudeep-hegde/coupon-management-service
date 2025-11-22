package com.monkcommerce.couponmanagementservice.repository;

import com.monkcommerce.couponmanagementservice.model.CouponRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CouponRuleRepository extends JpaRepository<CouponRule, UUID> {
    List<CouponRule> findByCouponIdOrderByPriorityAsc(UUID couponId);
}
