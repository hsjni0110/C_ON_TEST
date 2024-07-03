package com.example.c_on.menu.application;

import com.example.c_on.auth.exception.UnAuthorizationException;
import com.example.c_on.customer.domain.Customer;
import com.example.c_on.customer.domain.repository.CustomerRepository;
import com.example.c_on.customer.exception.NoSuchCustomerException;
import com.example.c_on.menu.domain.Category;
import com.example.c_on.menu.domain.Contain;
import com.example.c_on.menu.domain.Food;
import com.example.c_on.menu.domain.repository.CategoryRepository;
import com.example.c_on.menu.domain.repository.ContainsRepository;
import com.example.c_on.menu.domain.repository.FoodRepository;
import com.example.c_on.menu.dto.response.FoodRegisterResponse;
import com.example.c_on.menu.dto.response.FoodResponse;
import com.example.c_on.menu.exception.NoSuchCategoryException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class FoodService {

    private final FoodRepository foodRepository;
    private final CategoryRepository categoryRepository;
    private final ContainsRepository containsRepository;

    /**
     * 음식을 저장하는 메서드, Category와 Contain테이블도 함께 저장된다.
     * @param foodName
     * @param price
     * @param categoryName
     * @return
     */

    @Transactional
    public FoodRegisterResponse save(String foodName, Integer price, String categoryName) {
        Food food = new Food(foodName, price);
        Category category = getByCategoryName(categoryName);
        Contain contain = food.saveFoodWithCategory(category);
        containsRepository.save(contain);
        return FoodRegisterResponse.of(foodName, price);
    }

    /**
     * 카테고리를 가져오는 메서드
     * @param categoryName
     * @return
     */
    public Category getByCategoryName(String categoryName) {
        return categoryRepository.findById(categoryName)
                .orElseThrow(NoSuchCategoryException::new);
    }

    /**
     * 메뉴를 기반으로 검색하는 메서드, 최저 금액과 최고 금액을 지정할 수 있다.
     * @param foodName
     * @param lowerPrice
     * @param upperPrice
     * @return
     */
    public List<FoodResponse> getByFoodNameAndPrice(String foodName, Integer lowerPrice, Integer upperPrice) {
        List<Food> foods = foodRepository.findByNameAndPriceRange(
                foodName, lowerPrice, upperPrice);

        return foods.stream()
                .map(food -> new FoodResponse(food.getFoodName(), food.getPrice()))
                .toList();
    }

    public List<FoodResponse> findAll() {
        List<Food> all = foodRepository.findAll();
        return all.stream()
                .map(food -> new FoodResponse(food.getFoodName(), food.getPrice()))
                .toList();
    }

}
