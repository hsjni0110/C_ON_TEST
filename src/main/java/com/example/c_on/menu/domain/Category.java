package com.example.c_on.menu.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Table(name = "Category")
@AllArgsConstructor
@Getter
public class Category {

    @Id
    @Column(name = "categoryName", length = 100)
    private String categoryName;

    @Column(name = "noOfFood", nullable = false)
    private Integer noOfFood;

    @OneToMany(mappedBy = "category")
    private List<Contain> contains = new ArrayList<>();

    private Category(String categoryName) {
        this.categoryName = categoryName;
        this.noOfFood = 0;
    }

    public static Category initCategory(String categoryName) {
        return new Category(categoryName);
    }

    public Category() {
    }

    public void updateNumOfFood() {
        noOfFood++;
    }
}
