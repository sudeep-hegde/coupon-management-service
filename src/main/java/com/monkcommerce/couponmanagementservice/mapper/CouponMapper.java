package com.monkcommerce.couponmanagementservice.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monkcommerce.couponmanagementservice.dto.CouponRequestDto;
import com.monkcommerce.couponmanagementservice.dto.CouponResponse;
import com.monkcommerce.couponmanagementservice.model.Coupon;

import java.util.Map;

public class CouponMapper {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Coupon toEntity(CouponRequestDto req) {
        Coupon c = new Coupon();
        c.setCode(req.code());
        c.setType(req.type());
        c.setDetails(req.details());
        c.setStartDate(req.startDate());
        c.setEndDate(req.endDate());
        c.setMaxUses(req.maxUses());
//        c.setRepetitionLimit(req.repetitionLimit());
        c.setActive(req.active());
        return c;
    }

    public static void updateEntity(Coupon c, CouponRequestDto req) {
        c.setCode(req.code());
        c.setType(req.type());
        c.setDetails(req.details());
        c.setStartDate(req.startDate());
        c.setEndDate(req.endDate());
        c.setMaxUses(req.maxUses());
        c.setActive(req.active());
    }

    public static CouponResponse toResponse(Coupon c) {
        return new CouponResponse(
                c.getId(),
                c.getCode(),
                c.getType(),
                c.getDetails(),
                c.getStartDate(),
                c.getEndDate(),
                c.getMaxUses(),
//                c.getUses(),
//                c.getRepetitionLimit(),
                c.getActive(),
                c.getCreatedAt(),
                c.getUpdatedAt()
        );
    }

//    private static String toJson(Map<String, Object> map) {
//        try { return mapper.writeValueAsString(map); }
//        catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
