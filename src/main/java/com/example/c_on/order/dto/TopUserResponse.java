package com.example.c_on.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TopUserResponse {

    private String cno;
    private String name;
    private Integer completedOrders;
}
