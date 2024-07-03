package com.example.c_on.menu.presentation;

import com.example.c_on.auth.presentation.Auth;
import com.example.c_on.menu.dto.request.FoodSearchRequest;
import com.example.c_on.menu.dto.response.FoodRegisterResponse;
import com.example.c_on.menu.application.FoodService;
import com.example.c_on.menu.dto.request.FoodRegisterRequest;
import com.example.c_on.menu.dto.response.FoodResponse;
import java.net.URI;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
@AllArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @PostMapping("/register")
    public ResponseEntity<FoodRegisterResponse> registerFood(@RequestBody FoodRegisterRequest request) {
        FoodRegisterResponse response = foodService.save(request.getFoodName(), request.getPrice(), request.getCategoryName());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("")
    public ResponseEntity<List<FoodResponse>> findAll() {
        List<FoodResponse> foods = foodService.findAll();
        return ResponseEntity.ok(foods);
    }

//    @GetMapping("/{categoryName}")
//    public ResponseEntity<List<FoodResponse>> getAllByCategoryName(@PathVariable String categoryName) {
//        List<FoodResponse> foodResponses = foodService.getAllByCategoryName(categoryName);
//        return ResponseEntity.ok(foodResponses);
//    }

    @PostMapping("/search")
    public ResponseEntity<List<FoodResponse>> getByFoodNameAndPrice(
            @RequestBody FoodSearchRequest request
    ) {
        List<FoodResponse> foodResponses = foodService.getByFoodNameAndPrice(request.getFoodName(),
                request.getLowerPrice(), request.getUpperPrice());
        return ResponseEntity.ok(foodResponses);
    }

}
