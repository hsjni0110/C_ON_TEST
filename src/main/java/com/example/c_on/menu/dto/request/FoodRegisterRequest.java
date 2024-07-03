package com.example.c_on.menu.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FoodRegisterRequest {

    private String foodName;

    private Integer price;

    private String categoryName;
}
