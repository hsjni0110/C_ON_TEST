package com.example.c_on;

import com.example.c_on.customer.domain.repository.CustomerRepository;
import com.example.c_on.order.domain.repository.CartRepository;
import com.example.c_on.menu.domain.repository.CategoryRepository;
import com.example.c_on.menu.domain.repository.ContainsRepository;
import com.example.c_on.menu.domain.repository.FoodRepository;
import com.example.c_on.order.domain.repository.OrderDetailRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class ApiTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ContainsRepository containsRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @BeforeEach
    protected void setUp() {
        RestAssured.port = port;
        customerRepository.deleteAll();
        foodRepository.deleteAll();
        categoryRepository.deleteAll();
        cartRepository.deleteAll();
        containsRepository.deleteAll();
        orderDetailRepository.deleteAll();
    }
}
