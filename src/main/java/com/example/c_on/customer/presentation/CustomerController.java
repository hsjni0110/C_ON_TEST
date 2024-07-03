package com.example.c_on.customer.presentation;

import com.example.c_on.auth.application.AuthService;
import com.example.c_on.auth.domain.AuthToken;
import com.example.c_on.auth.dto.response.AccessAndRefreshTokenResponse;
import com.example.c_on.auth.dto.response.CustomerInfo;
import com.example.c_on.customer.application.CustomerSerivce;
import com.example.c_on.customer.dto.request.CustomerLoginRequest;
import com.example.c_on.customer.dto.request.CustomerRegisterRequest;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerSerivce customerSerivce;
    private final AuthService authService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void register(@RequestBody CustomerRegisterRequest request) {
        customerSerivce.register(request.getUsername(), request.getPassword(), request.getPhoneNumber());
    }

    @PostMapping("/login")
    public ResponseEntity<AccessAndRefreshTokenResponse> login(@RequestBody CustomerLoginRequest request) {
        CustomerInfo customer = customerSerivce.login(request.getUsername(), request.getPassword());
        AccessAndRefreshTokenResponse tokens = authService.generateAccessAndRefreshToken(customer.getCustomerName(), customer.getCno());
        return ResponseEntity.ok(tokens);
    }
}
