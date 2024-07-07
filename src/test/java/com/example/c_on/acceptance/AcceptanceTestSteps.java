package com.example.c_on.acceptance;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.assertj.core.api.Assertions.*;
import org.springframework.http.HttpStatus;

public class AcceptanceTestSteps {

    public static final HttpStatus 생성됨 = HttpStatus.CREATED;
    public static final HttpStatus 정상_처리 = HttpStatus.OK;
    public static final HttpStatus 잘못된_요청 = HttpStatus.BAD_REQUEST;

    public static RequestSpecification given(String accessToken) {
        return RestAssured
                .given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON);
    }

    public static void 응답_상태를_검증한다(
            ExtractableResponse<Response> 응답,
            HttpStatus 예상_상태) {
        assertThat(응답.statusCode()).isEqualTo(예상_상태.value());
    }
}
