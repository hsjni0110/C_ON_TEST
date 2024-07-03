package com.example.c_on.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MostFoodByCategoryResponse {

    private String CategoryName;
    private String foodName;
    private Integer totalQuantity;
}
