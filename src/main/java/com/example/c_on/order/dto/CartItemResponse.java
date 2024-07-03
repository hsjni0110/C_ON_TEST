package com.example.c_on.order.dto;

import com.example.c_on.order.domain.OrderDetail;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartItemResponse {

    private String foodName;
    private Integer quantity;
    private Integer price;

    public static CartItemResponse of(OrderDetail orderDetail) {
        Integer totalPrice = orderDetail.getTotalPrice();
        return new CartItemResponse(orderDetail.getFood().getFoodName(), orderDetail.getQuantity(), totalPrice);
    }
}
