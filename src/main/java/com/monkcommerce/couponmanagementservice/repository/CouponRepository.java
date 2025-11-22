package com.monkcommerce.couponmanagementservice.repository;

import com.monkcommerce.couponmanagementservice.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CouponRepository extends JpaRepository<Coupon, UUID> {
    Optional<Coupon> findByCode(String code);
    boolean existsByCode(String code);

    @Modifying
    @Query("""
        UPDATE Coupon c
        SET c.usedCount = c.usedCount + 1
        WHERE c.id = :id
        AND c.usedCount < c.maxUses
    """)
    int incrementUsage(UUID id);

    List<Coupon> findByActiveTrue();
}
