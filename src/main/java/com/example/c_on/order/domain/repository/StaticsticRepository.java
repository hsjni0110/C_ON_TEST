package com.example.c_on.order.domain.repository;

public interface StaticsticRepository {

    Object[] getMostPopularFood();
    Object[] getMostPopularFoodByCategory();
    Object[] getMostOrderedCustomer();
}
