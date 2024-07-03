package com.example.c_on.acceptance;

import com.example.c_on.customer.domain.Customer;
import com.example.c_on.order.domain.Cart;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class OrderAcceptanceSteps {

    public static Customer 회원의_장바구니를_생성한다(Customer customer) {
        customer.addCart();
        return customer;
    }
}
