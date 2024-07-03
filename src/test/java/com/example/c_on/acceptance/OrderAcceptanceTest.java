package com.example.c_on.acceptance;

import com.example.c_on.acceptance.fixture.AuthAcceptanceFixtures;
import com.example.c_on.customer.domain.Customer;
import com.example.c_on.menu.domain.Category;
import com.example.c_on.menu.domain.Food;
import com.example.c_on.order.domain.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static com.example.c_on.customer.fixture.CustomerFixture.*;
import static com.example.c_on.acceptance.OrderAcceptanceSteps.*;
import static com.example.c_on.acceptance.fixture.AuthAcceptanceFixtures.로그인_후_엑세스_토큰을_반환한다;
import static com.example.c_on.menu.fixture.MenuFixture.*;

@DisplayName("주문 인수 테스트")
public class OrderAcceptanceTest extends AcceptanceTest{

    @Nested
    class 주문_API {

        private final Customer 석진 = 석진();
        private final Category 양식 = 양식();
//        private final Food 돈까스 = 돈까스(양식);

        @BeforeEach
        void setUp() {
            testData.addCustomers(석진);
            testData.addCategory(양식);
//            testData.addFoods(돈까스);
        }

        @Test
        void 회원은_장바구니에_담긴_음식을_주문할_수_있다() {
            // given
            String accessToken = 로그인_후_엑세스_토큰을_반환한다(석진);
//            회원이_장바구니에_음식을_담는다();

            // when



            // then
        }
    }
}

