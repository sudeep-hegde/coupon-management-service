package com.monkcommerce.couponmanagementservice.repository;

import com.monkcommerce.couponmanagementservice.model.CouponRule;
import com.monkcommerce.couponmanagementservice.model.CouponUsage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UsageRepository extends JpaRepository<CouponUsage, UUID> {
    int countByCouponId(UUID couponId);

    int countByCouponIdAndUserId(UUID couponId, String userId);

    boolean existsByCouponIdAndCartId(UUID couponId, String cartId);

}
