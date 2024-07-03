package com.example.c_on.order.dto;

import com.example.c_on.order.domain.Cart;
import com.example.c_on.order.domain.OrderDetail;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartInfo {

    private LocalDateTime orderDateTime;
    private List<OrderDetailInfo> orderDetailInfoList;

    @Builder
    @Getter
    @AllArgsConstructor
    public static class OrderDetailInfo {

        private String foodName;
        private Integer quantity;
    }

    public static CartInfo of(Cart carts) {

        List<OrderDetailInfo> orderDetailInfos = carts.getOrderDetails().stream()
                .map(CartInfo::getOrderDetailInfo)
                .toList();
        return new CartInfo(carts.getOrderDateTime(), orderDetailInfos);
    }

    public static OrderDetailInfo getOrderDetailInfo(OrderDetail orderDetail) {
        return new OrderDetailInfo(orderDetail.getFood().getFoodName(), orderDetail.getQuantity());
    }
}
