package com.example.c_on.menu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryResponse {

    private String categoryName;
    private Integer numOfFoods;
}
