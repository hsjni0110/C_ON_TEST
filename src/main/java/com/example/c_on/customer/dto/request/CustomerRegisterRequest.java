package com.example.c_on.customer.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerRegisterRequest {

    private String username;
    private String password;
    private String phoneNumber;
}
