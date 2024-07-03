package com.example.c_on.customer.presentation;

import static org.junit.jupiter.api.Assertions.*;

import com.example.c_on.ApiTest;
import com.example.c_on.auth.dto.response.AccessAndRefreshTokenResponse;
import com.example.c_on.customer.dto.request.CustomerLoginRequest;
import com.example.c_on.customer.dto.request.CustomerRegisterRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("고객 컨트롤러 (CustomerController) 은(는)")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class CustomerControllerTest extends ApiTest {

        @Test
        void RestController_빈으로_등록한다() {
            // when
            boolean isRestController = CustomerController.class.isAnnotationPresent(RestController.class);

            // then
            assertThat(isRestController).isTrue();
        }

        @Nested
        class 회원가입_시 {

            @Test
            void 성공하면_201_상태코드_반환() {
                // given
                CustomerRegisterRequest request = new CustomerRegisterRequest(
                        "회원이름",
                        "password",
                        "010-0000-0000"
                );

                // when
                ExtractableResponse<Response> response = RestAssured.given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body(request)
                        .post("/customer/register")
                        .then()
                        .log().all()
                        .extract();

                // then
                assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
            }
        }

        @Nested
        class 로그인_시 {

            @BeforeEach
            void setUp() {
                // given
                CustomerRegisterRequest request = new CustomerRegisterRequest(
                        "회원이름",
                        "password",
                        "010-0000-0000"
                );
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(request)
                        .post("/customer/register")
                        .then()
                        .log().all()
                        .extract();
            }

            @Test
            void 로그인에_성공하면_200_상태코드와_AccessAndRefreshToken_을_반환한다() {
                // given
                CustomerLoginRequest request = new CustomerLoginRequest(
                        "회원이름",
                        "password"
                );

                // when
                ExtractableResponse<Response> response = RestAssured.given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body(request)
                        .post("/customer/login")
                        .then()
                        .log().all()
                        .extract();

                // then
                assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
                assertThat(response.as(AccessAndRefreshTokenResponse.class).getAccessToken())
                        .isNotNull();
            }
        }
}