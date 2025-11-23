-- Please run these queries before executing the apply-coupon and applicable-coupon endpoints
-- NEWYEAR50: CART_WISE percentage 50% (max discount 500)
INSERT INTO coupons (id, code, type, start_date, end_date, max_uses, per_user_limit, stacking_type, used_count, reserved_count, active, created_at, updated_at)
VALUES ('11111111-1111-1111-1111-111111111111', 'NEWYEAR50', 'CART_WISE',
        '2025-01-01 00:00:00', '2025-12-31 23:59:59',
        100, 2, 'STACKABLE', 0, 0, true, now(), now());

-- SHOES20: PRODUCT_WISE flat 200 on product P-SHOE
INSERT INTO coupons (id, code, type, start_date, end_date, max_uses, per_user_limit, stacking_type, used_count, reserved_count, active, created_at, updated_at)
VALUES ('22222222-2222-2222-2222-222222222222', 'SHOES20', 'PRODUCT_WISE',
        '2025-01-01 00:00:00', '2025-12-31 23:59:59',
        500, 5, 'STACKABLE', 0, 0, true, now(), now());

-- BUY2GET1: BXGY buy product P1 get product P2 free
INSERT INTO coupons (id, code, type, start_date, end_date, max_uses, per_user_limit, stacking_type, used_count, reserved_count, active, created_at, updated_at)
VALUES ('33333333-3333-3333-3333-333333333333', 'BUY2GET1', 'BXGY',
        '2025-01-01 00:00:00', '2025-12-31 23:59:59',
        300, 3, 'EXCLUSIVE', 0, 0, true, now(), now());

-- EXPIRED coupon example
INSERT INTO coupons (id, code, type, start_date, end_date, max_uses, per_user_limit, stacking_type, used_count, reserved_count, active, created_at, updated_at)
VALUES ('44444444-4444-4444-4444-444444444444', 'OLDCOUPON', 'CART_WISE',
        '2023-01-01 00:00:00', '2023-12-31 23:59:59',
        100, 2, 'STACKABLE', 0, 0, true, now(), now());

-- ALREADY_EXHAUSTED coupon (maxUses=1 used_count=1)
INSERT INTO coupons (id, code, type, start_date, end_date, max_uses, per_user_limit, stacking_type, used_count, reserved_count, active, created_at, updated_at)
VALUES ('55555555-5555-5555-5555-555555555555', 'ONEUSE', 'CART_WISE',
        '2025-01-01 00:00:00', '2025-12-31 23:59:59',
        1, 1, 'STACKABLE', 1, 0, true, now(), now());


-- NEWYEAR50: PERCENTAGE rule: 50% up to maxDiscount 500
INSERT INTO coupon_rules (id, coupon_id, rule_type, rule_config, priority, created_at, updated_at)
VALUES (
           'aaaaaaaa-0000-0000-0000-aaaaaaaaaaaa',
           '11111111-1111-1111-1111-111111111111',
           'PERCENTAGE',
           '{"percent": 50, "maxDiscount": 500}'::jsonb,
           10,
           now(),
           now()
       );

-- SHOES20: FLAT rule on productId "P-SHOE" amount 200
INSERT INTO coupon_rules (id, coupon_id, rule_type, rule_config, priority, created_at, updated_at)
VALUES (
           'bbbbbbbb-0000-0000-0000-bbbbbbbbbbbb',
           '22222222-2222-2222-2222-222222222222',
           'FLAT',
           '{"amount": 200, "productId": "P-SHOE"}'::jsonb,
           10,
           now(),
           now()
       );

-- BUY2GET1: B_X_G_Y rule: buy product "P1" qty 2 get "P2" qty 1
INSERT INTO coupon_rules (id, coupon_id, rule_type, rule_config, priority, created_at, updated_at)
VALUES (
           'cccccccc-0000-0000-0000-cccccccccccc',
           '33333333-3333-3333-3333-333333333333',
           'B_X_G_Y',
           '{"buy": {"productId": "P1", "qty": 2}, "get": {"productId": "P2", "qty": 1}}'::jsonb,
           10,
           now(),
           now()
       );

-- Additional rule: BUY2GET1 also has a validation rule (optional) to restrict to category "toys"
INSERT INTO coupon_rules (id, coupon_id, rule_type, rule_config, priority, created_at, updated_at)
VALUES (
           'dddddddd-0000-0000-0000-dddddddddddd',
           '33333333-3333-3333-3333-333333333333',
           'CATEGORY_INCLUDE',
           '{"categories": ["toys", "kids"]}'::jsonb,
           5,
           now(),
           now()
       );
INSERT INTO coupon_usage (id, coupon_id, user_id, cart_id, discount_applied, cart_snapshot, applied_at)
VALUES (
           '77777777-7777-7777-7777-777777777777',
           '22222222-2222-2222-2222-222222222222',
           'U_100',
           'CART_100',
           200.00,
           '{"items":[{"productId":"P-SHOE","quantity":1,"price":1000,"category":"shoes"}]}'::jsonb,
           now()
       );
