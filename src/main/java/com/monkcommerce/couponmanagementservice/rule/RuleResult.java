package com.monkcommerce.couponmanagementservice.rule;

public record RuleResult(
    boolean valid,
    double discount,
    String message
) {}
