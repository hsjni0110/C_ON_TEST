package com.example.c_on.acceptance.fixture;

import static com.example.c_on.acceptance.AcceptanceTestSteps.given;

import com.example.c_on.menu.domain.Contain;

public class OrderAcceptanceFixtures {

    public static void 회원이_장바구니에_음식을_담는_요청(String accessToken, Contain contain) {
        given(accessToken)
            .when()
            .post("/" + contain.getFood().getFoodName())
            .then().log().all()
            .extract();
    }
}
