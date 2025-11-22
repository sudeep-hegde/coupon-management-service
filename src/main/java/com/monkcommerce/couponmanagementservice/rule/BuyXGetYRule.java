package com.monkcommerce.couponmanagementservice.rule;

import com.monkcommerce.couponmanagementservice.dto.CartItemDto;
import com.monkcommerce.couponmanagementservice.dto.FreeItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BuyXGetYRule implements RuleEvaluator{
    @Override
    public String type() { return "B_X_G_Y"; }

    @Override
    public boolean isDiscountRule() {
        return true;
    }

    @Override
    public RuleResult evaluate(RuleEvaluationContext ctx, Map<String, Object> config) {

        Map<String,Object> buy = (Map<String,Object>) config.get("buy");
        Map<String,Object> get = (Map<String,Object>) config.get("get");

        String buyProduct = (String) buy.get("productId");
        int buyQty = (int) buy.get("qty");

        String freeProduct = (String) get.get("productId");
        int freeQty = (int) get.get("qty");


        int userBuyQty = ctx.items().stream()
                .filter(i -> i.productId().equals(buyProduct))
                .mapToInt(CartItemDto::quantity)
                .sum();

        if (userBuyQty < buyQty) {
            return RuleResult.fail("Buy X condition not satisfied");
        }

        int eligibleFreeUnits = (userBuyQty / buyQty) * freeQty;

        double freeUnitPrice = ctx.items().stream()
                .filter(i -> i.productId().equals(freeProduct))
                .mapToDouble(CartItemDto::price)
                .findFirst()
                .orElse(0.0);

        double discount = eligibleFreeUnits * freeUnitPrice;

        FreeItem freeItem = new FreeItem(
                freeProduct,
                eligibleFreeUnits
        );

        return RuleResult.okWithFreeItems(
                discount,
                "Buy X Get Y applied",
                List.of(freeItem)
        );
    }

}
