package com.monkcommerce.couponmanagementservice.service;

import com.monkcommerce.couponmanagementservice.dto.CouponRequestDto;
import com.monkcommerce.couponmanagementservice.dto.CouponResponse;
import com.monkcommerce.couponmanagementservice.exception.ResourceAlreadyExistsException;
import com.monkcommerce.couponmanagementservice.exception.ResourceNotFoundException;
import com.monkcommerce.couponmanagementservice.mapper.CouponMapper;
import com.monkcommerce.couponmanagementservice.model.Coupon;
import com.monkcommerce.couponmanagementservice.repository.CouponRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository repository;

    public CouponServiceImpl(CouponRepository repository) {
        this.repository = repository;
    }

    @Override
    public CouponResponse createCoupon(CouponRequestDto requestDto) {
        if(repository.existsByCode(requestDto.code())) {
            throw new ResourceAlreadyExistsException("Coupon code already exists");
        }

        Coupon coupon = CouponMapper.toEntity(requestDto);
        coupon = repository.save(coupon);

        return CouponMapper.toResponse(coupon);
    }

    @Override
    public CouponResponse getCoupon(UUID id) {
        Coupon coupon = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found"));
        return CouponMapper.toResponse(coupon);
    }

    @Override
    public Page<CouponResponse> getAllCoupons(int page, int size) {
        return repository.findAll(PageRequest.of(page, size))
                .map(CouponMapper::toResponse);
    }

    @Override
    public CouponResponse updateCoupon(UUID id, CouponRequestDto request) {
        Coupon existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found"));

        CouponMapper.updateEntity(existing, request);

        return CouponMapper.toResponse(repository.save(existing));
    }

    @Override
    public void deleteCoupon(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Coupon not found");
        }
        repository.deleteById(id);
    }
}
