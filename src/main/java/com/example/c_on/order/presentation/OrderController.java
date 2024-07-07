package com.example.c_on.order.presentation;

import com.example.c_on.auth.dto.response.CustomerInfo;
import com.example.c_on.auth.presentation.Auth;
import com.example.c_on.menu.dto.response.FoodResponse;
import com.example.c_on.order.application.AdminService;
import com.example.c_on.order.application.OrderService;
import com.example.c_on.order.dto.CartInfo;
import com.example.c_on.order.dto.CartInfoRequest;
import com.example.c_on.order.dto.CartItemResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/cart")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{foodName}")
    public ResponseEntity<Void> addToCart(
            @Auth String customerName,
            @PathVariable String foodName)
    {
        orderService.addOrderDetail(customerName, foodName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping()
    public ResponseEntity<List<CartItemResponse>> findAll(
            @Auth String customerName
    ) {
        List<CartItemResponse> cartItemResponses = orderService.findAll(customerName);
        return ResponseEntity.ok(cartItemResponses);
    }

    @PatchMapping("/increase/{foodName}")
    public ResponseEntity<Void> increaseCartItem(
            @Auth String customerName,
            @PathVariable String foodName
    ) {
        orderService.increaseCartItem(customerName, foodName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/decrease/{foodName}")
    public ResponseEntity<Void> decreaseCartItem (
            @Auth String customerName,
            @PathVariable String foodName
    ) {
        orderService.decreaseCartItem(customerName, foodName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/order")
    public ResponseEntity<Void> order(
            @Auth String customerName
    ) {
        orderService.order(customerName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/previous")
    public ResponseEntity<List<CartInfo>> findPreviousCartInfo(
            @Auth String customerName,
            @RequestBody CartInfoRequest request
    ) {
        List<CartInfo> previousCartInfo = orderService.findPreviousCartInfo(customerName, request.getStartDate(),
                request.getEndDate());
        return ResponseEntity.ok(previousCartInfo);
    }

    @GetMapping("/previous/all")
    public ResponseEntity<List<CartInfo>> findPreviousCartInfoAll(
            @Auth String customerName
    ) {
        List<CartInfo> previousCartInfo = orderService.findPreviousCartInfoAll(customerName);
        return ResponseEntity.ok(previousCartInfo);
    }
}
