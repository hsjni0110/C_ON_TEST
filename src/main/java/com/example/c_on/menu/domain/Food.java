package com.example.c_on.menu.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "Food")
@Getter
@Slf4j
public class Food {

    @Id
    @Column(name = "foodName", length = 100)
    private String foodName;

    @Column(name = "price", nullable = false)
    private Integer price;

    @OneToMany(mappedBy = "food")
    private List<Contain> contains = new ArrayList<>();;

    public Food() {
    }

    public Food(String foodName, Integer price) {
        this.foodName = foodName;
        this.price = price;
    }

    public Contain saveFoodWithCategory(Category category) {
        category.updateNumOfFood();
        Contain contain = new Contain(this, category);
        contains.add(contain);
        return contain;
    }
}
