package com.monkcommerce.couponmanagementservice.dto;

public record CartItemDto(
    String productId,
    Integer quantity,
    Double price,
    String category
) {}
