package com.monkcommerce.couponmanagementservice.dto;

public record ApplyCouponResponse(boolean valid,
                                  double discount,
                                  double finalPrice,
                                  String message) {
}
