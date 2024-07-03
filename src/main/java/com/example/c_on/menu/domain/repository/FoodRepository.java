package com.example.c_on.menu.domain.repository;

import com.example.c_on.menu.domain.Food;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FoodRepository extends JpaRepository<Food, String> {

    Optional<Food> findFoodByFoodName(String foodName);

    @Query("SELECT f FROM Food f WHERE f.foodName LIKE CONCAT('%', :foodName, '%') AND f.price BETWEEN :lowerPrice AND :upperPrice")
    List<Food> findByNameAndPriceRange(@Param("foodName") String foodName, @Param("lowerPrice") Integer lowerPrice, @Param("upperPrice") Integer upperPrice);
}