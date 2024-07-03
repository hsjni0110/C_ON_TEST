package com.example.c_on.order.presentation;

import com.example.c_on.auth.presentation.Auth;
import com.example.c_on.order.application.AdminService;
import com.example.c_on.order.dto.MostFoodByCategoryResponse;
import com.example.c_on.order.dto.MostFoodResponse;
import com.example.c_on.order.dto.TopUserResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/statistic")
public class StatisticController {

    private final AdminService adminService;

    @GetMapping("/most-sold")
    public ResponseEntity<MostFoodResponse> getMostSold(
            @Auth String customerName
    ) {
        MostFoodResponse mostSold = adminService.getMostSold(customerName);
        return ResponseEntity.ok(mostSold);
    }

    @GetMapping("/most-sold-by-category/{categoryName}")
    public ResponseEntity<MostFoodByCategoryResponse> getMostSoldByCategory(
            @Auth String customerName,
            @PathVariable String categoryName
    ) {
        MostFoodByCategoryResponse mostSoldByCategory = adminService.getMostSoldByCategory(customerName, categoryName);
        return ResponseEntity.ok(mostSoldByCategory);
    }

    @GetMapping("/top-user")
    public ResponseEntity<List<TopUserResponse>> getTopCustomer(
            @Auth String customerName
    ) {
        List<TopUserResponse> topCustomer = adminService.getTopCustomer(customerName);
        return ResponseEntity.ok(topCustomer);
    }
}
