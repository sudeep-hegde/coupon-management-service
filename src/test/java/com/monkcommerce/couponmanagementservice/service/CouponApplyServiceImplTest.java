package com.monkcommerce.couponmanagementservice.service;

import com.monkcommerce.couponmanagementservice.dto.ApplyCouponRequest;
import com.monkcommerce.couponmanagementservice.dto.CartItemDto;
import com.monkcommerce.couponmanagementservice.exception.CouponValidationException;
import com.monkcommerce.couponmanagementservice.exception.ResourceNotFoundException;
import com.monkcommerce.couponmanagementservice.model.Coupon;
import com.monkcommerce.couponmanagementservice.repository.CouponRepository;
import com.monkcommerce.couponmanagementservice.repository.CouponRuleRepository;
import com.monkcommerce.couponmanagementservice.repository.UsageRepository;
import com.monkcommerce.couponmanagementservice.rule.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CouponApplyServiceImplTest {
    @Mock
    private CouponRepository couponRepository;

    @Mock
    private RuleEngine ruleEngine;

    @Mock
    private CouponRuleRepository ruleRepository;

    @Mock
    private CouponValidator validator;

    @Mock
    private UsageRepository usageRepository;

    @InjectMocks
    private CouponApplyServiceImpl service;

    private Coupon coupon;
    private ApplyCouponRequest request;

    @BeforeEach
    void setup() {
        coupon = Coupon.builder()
                .id(UUID.randomUUID())
                .code("TEST10")
                .startDate(LocalDateTime.now().minusDays(1))
                .endDate(LocalDateTime.now().plusDays(1))
                .maxUses(10)
                .usedCount(2)
                .perUserLimit(5)
                .active(true)
                .build();

        request = new ApplyCouponRequest(
                "TEST10",
                "U1",
                "C1",
                List.of(new CartItemDto("P1", 2, 100.0, "cat1"))
        );
    }

    @Test
    void testCouponNotFound() {
        when(couponRepository.findByCode("TEST10"))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.applyCoupon(request));
    }

    @Test
    void testValidationFailure() {
        when(couponRepository.findByCode("TEST10"))
                .thenReturn(Optional.of(coupon));

        doThrow(new CouponValidationException("Expired"))
                .when(validator).validate(eq(coupon), any(ApplyCouponRequest.class));

        assertThrows(CouponValidationException.class,
                () -> service.applyCoupon(request));
    }
}
