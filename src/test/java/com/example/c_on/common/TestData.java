package com.example.c_on.common;

import com.example.c_on.customer.domain.Customer;
import com.example.c_on.menu.domain.Category;
import com.example.c_on.menu.domain.Food;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Builder.Default;

public class TestData {

    @Default
    private final List<Customer> customers = new ArrayList<>();

    @Default
    private final List<Category> categories = new ArrayList<>();

    @Default
    private final List<Food> foods = new ArrayList<>();

    public void addCustomers(Customer... customers) {
        addCustomers(Arrays.asList(customers));
    }

    private void addCustomers(List<Customer> customers) {
        this.customers.addAll(customers);
    }

    public void addCategory(Category... categories) {
        addCategory(Arrays.asList(categories));
    }

    private void addCategory(List<Category> categories) {
        this.categories.addAll(categories);
    }

    public void addFoods(Food... foods) {
        addFoods(Arrays.asList(foods));
    }

    private void addFoods(List<Food> foods) {
        this.foods.addAll(foods);
    }

    public List<Customer> customers() {
        return customers;
    }
}