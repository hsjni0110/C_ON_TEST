package com.example.c_on.menu.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FoodSearchRequest {

    private String foodName;

    private Integer lowerPrice;

    private Integer upperPrice;
}
