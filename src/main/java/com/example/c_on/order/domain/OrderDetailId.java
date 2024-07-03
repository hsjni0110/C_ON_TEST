package com.example.c_on.order.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class OrderDetailId implements Serializable {
    private UUID itemNo;
    private String cart;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderDetailId that = (OrderDetailId) o;
        return Objects.equals(itemNo, that.itemNo) && Objects.equals(cart, that.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemNo, cart);
    }
}
