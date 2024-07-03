package com.example.c_on.customer.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.example.c_on.auth.exception.UnAuthorizationException;
import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CustomerTest {

    @Test
    void 회원의_비밀번호가_일치하지_않으면_에러를_반환한다() {
        // given
        Customer customer = new Customer("c1", "customerName", "123", "010-0000-0000");

        // when, then
        assertThatThrownBy(() -> customer.validatePassword("hihi"))
                .isInstanceOf(UnAuthorizationException.class);
    }

    @Test
    void 새로운_카트는_주문일자가_NULL_이다() {
        // given
        Customer customer = new Customer("c1", "customerName", "123", "010-0000-0000");

        // when
        customer.addCart();

        // then
        assertThat(customer.getCarts().get(0).getOrderDateTime()).isNull();
    }
}