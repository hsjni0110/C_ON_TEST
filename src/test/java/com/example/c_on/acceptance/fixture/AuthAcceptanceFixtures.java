package com.example.c_on.acceptance.fixture;

import com.example.c_on.auth.dto.response.AccessAndRefreshTokenResponse;
import com.example.c_on.customer.domain.Customer;
import com.example.c_on.customer.dto.request.CustomerLoginRequest;
import io.restassured.RestAssured;
import org.springframework.http.MediaType;

public class AuthAcceptanceFixtures {

    public static String 로그인_후_엑세스_토큰을_반환한다(final Customer customer) {
        AccessAndRefreshTokenResponse accessAndRefreshTokenResponse = RestAssured.given().log().all()
                .body(new CustomerLoginRequest(customer.getName(), customer.getPassword()))
                .when()
                .post("/customer/login")
                .then()
                .log().all()
                .extract()
                .as(AccessAndRefreshTokenResponse.class);
        return accessAndRefreshTokenResponse.getAccessToken();
    }
}
