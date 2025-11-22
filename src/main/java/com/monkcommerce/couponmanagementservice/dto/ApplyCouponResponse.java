package com.monkcommerce.couponmanagementservice.dto;

import java.util.List;

public record ApplyCouponResponse(boolean valid,
                                  double discount,
                                  double finalPrice,
                                  String message,
                                  List<FreeItem> freeItems) {
}
