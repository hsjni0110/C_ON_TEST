package com.example.c_on.menu.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class CategoryRegisterRequest {

    private String categoryName;

    @JsonCreator
    public CategoryRegisterRequest(String categoryName) {
        this.categoryName = categoryName;
    }
}
