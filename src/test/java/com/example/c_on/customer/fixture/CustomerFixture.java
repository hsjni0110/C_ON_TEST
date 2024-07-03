package com.example.c_on.customer.fixture;

import com.example.c_on.customer.domain.Customer;

public class CustomerFixture {

    public static Customer 석진() {
        Customer 석진 = Customer.builder()
                .cno("c1")
                .name("석진")
                .phoneNumber("010-0000-0101")
                .build();
        석진.addCart();
        return 석진;
    }
}
