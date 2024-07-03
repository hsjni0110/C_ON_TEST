package com.example.c_on.menu.domain;

import com.example.c_on.menu.dto.response.FoodResponse;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Entity
@Table(name = "Contain")
@Getter
public class Contain {

    @EmbeddedId
    ContainId containId;

    @MapsId("foodName")
    @ManyToOne(cascade = CascadeType.ALL)
    private Food food;

    @MapsId("categoryName")
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    public Contain() {
    }

    public Contain(Food food, Category category) {
        this.food = food;
        this.category = category;
        this.containId = new ContainId(food.getFoodName(), category.getCategoryName());
    }

    public static FoodResponse mapToFoodResponse(Contain contain) {
        return new FoodResponse(contain.getFood().getFoodName(), contain.getFood().getPrice());
    }
}