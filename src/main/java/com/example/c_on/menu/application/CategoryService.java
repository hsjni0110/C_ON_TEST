package com.example.c_on.menu.application;

import com.example.c_on.menu.domain.Category;
import com.example.c_on.menu.domain.repository.CategoryRepository;
import com.example.c_on.menu.dto.response.CategoryResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void register(String categoryName) {
        categoryRepository.save(Category.initCategory(categoryName));
    }

    public List<CategoryResponse> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> new CategoryResponse(category.getCategoryName(), category.getNoOfFood()))
                .toList();
    }
}
