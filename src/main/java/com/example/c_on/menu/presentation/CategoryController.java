package com.example.c_on.menu.presentation;

import com.example.c_on.menu.application.CategoryService;
import com.example.c_on.menu.dto.request.CategoryRegisterRequest;
import com.example.c_on.menu.dto.response.CategoryResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerCategory(@RequestBody CategoryRegisterRequest request) {
        categoryService.register(request.getCategoryName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryResponse>> findAll() {
        List<CategoryResponse> categoryResponse = categoryService.findAll();
        return ResponseEntity.ok(categoryResponse);
    }
}
