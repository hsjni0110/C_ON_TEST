package com.example.c_on.menu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.PostMapping;

@Getter
@AllArgsConstructor
public class FoodResponse {

    private String foodName;
    private Integer foodPrice;
}
