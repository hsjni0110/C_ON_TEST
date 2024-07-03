package com.example.c_on.menu.fixture;

import com.example.c_on.menu.domain.Category;
import com.example.c_on.menu.domain.Contain;
import com.example.c_on.menu.domain.Food;

public class MenuFixture {

    public static Category 양식() {
        return Category.initCategory("양식");
    }

    public static Contain 돈까스(Category 양식) {
        Food food = new Food("돈까스", 10000);
        return food.saveFoodWithCategory(양식);

    }
}
