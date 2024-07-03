package com.example.c_on.menu.domain.repository;

import com.example.c_on.menu.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
