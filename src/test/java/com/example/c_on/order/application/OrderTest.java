package com.example.c_on.order.application;

import static com.example.c_on.customer.fixture.CustomerFixture.석진;
import static com.example.c_on.menu.fixture.MenuFixture.돈까스;
import static com.example.c_on.menu.fixture.MenuFixture.양식;
import static org.assertj.core.api.Assertions.*;

import com.example.c_on.common.IntegrationTest;
import com.example.c_on.customer.domain.Customer;
import com.example.c_on.menu.domain.Category;
import com.example.c_on.menu.domain.Contain;
import com.example.c_on.menu.domain.Food;
import com.example.c_on.order.domain.Cart;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderTest extends IntegrationTest {

    private Category 양식;
    private Contain 돈까스;
    private Customer 석진;

    @BeforeEach
    void setUp() {
        양식 = categoryRepository.save(양식());
        돈까스 = containsRepository.save(돈까스(양식));
        석진 = customerRepository.save(석진());
    }

    @Test
    void 회원이_장바구니에_음식을_담는다() {
        // given, when
        orderService.addOrderDetail(석진.getName(), 돈까스.getFood().getFoodName());

        // then
        Optional<Cart> cart = cartRepository.findById(석진.getCarts().get(0).getId());
        assertThat(cart).isPresent();
        assertThat(cart.get().findOrderDetailByFoodName(돈까스.getFood().getFoodName()).getFoodName()).isEqualTo("돈까스");
    }
}
