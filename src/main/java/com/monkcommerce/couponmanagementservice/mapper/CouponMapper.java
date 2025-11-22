package com.monkcommerce.couponmanagementservice.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monkcommerce.couponmanagementservice.dto.CouponRequestDto;
import com.monkcommerce.couponmanagementservice.dto.CouponResponse;
import com.monkcommerce.couponmanagementservice.model.Coupon;

import java.util.Map;

public class CouponMapper {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Coupon toEntity(CouponRequestDto req) {
       return Coupon.builder()
               .code(req.code())
               .type(req.type())
               .startDate(req.startDate())
               .endDate(req.endDate())
               .maxUses(req.maxUses())
               .perUserLimit(req.perUserLimit())
               .stackingType(req.stackingType())
               .active(req.active())
               .build();
    }

    public static void updateEntity(Coupon entity, CouponRequestDto dto) {
        entity.setType(dto.type());
        entity.setStartDate(dto.startDate());
        entity.setEndDate(dto.endDate());
        entity.setMaxUses(dto.maxUses());
        entity.setPerUserLimit(dto.perUserLimit());
        entity.setStackingType(dto.stackingType());
        entity.setActive(dto.active());
    }

    public static CouponResponse toResponse(Coupon c) {
        return new CouponResponse(
                c.getId(),
                c.getCode(),
                c.getType(),
                c.getStartDate(),
                c.getEndDate(),
                c.getMaxUses(),
                c.getPerUserLimit(),
                c.getStackingType(),
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
