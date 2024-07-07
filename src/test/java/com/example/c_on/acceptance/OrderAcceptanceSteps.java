package com.example.c_on.acceptance;

import static com.example.c_on.acceptance.AcceptanceTestSteps.given;

import com.example.c_on.customer.domain.Customer;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class OrderAcceptanceSteps {

    public static Customer 회원의_장바구니를_생성한다(Customer customer) {
        customer.addCart();
        return customer;
    }

    public static ExtractableResponse<Response> 음식을_주문한다(String accessToken) {
        return given(accessToken)
                .when()
                .post("/cart/order")
                .then().log().all()
                .extract();
    }
}
