package com.monkcommerce.couponmanagementservice.validator;

import com.monkcommerce.couponmanagementservice.dto.CouponRequestDto;
import com.monkcommerce.couponmanagementservice.validator.annotation.ValidDateRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, CouponRequestDto> {

    @Override
    public boolean isValid(CouponRequestDto dto, ConstraintValidatorContext context) {

        if (dto == null) return true;

        if (dto.startDate() == null || dto.endDate() == null)
            return true; // let @NotNull handle it

        return dto.startDate().isBefore(dto.endDate());
    }
}
