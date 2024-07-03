package com.example.c_on.menu.dto.response;

import com.example.c_on.menu.domain.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FoodRegisterResponse {

    private String foodName;

    private Integer price;

    public static FoodRegisterResponse of(String foodName, Integer price) {
        return new FoodRegisterResponse(foodName, price);
    }

}
