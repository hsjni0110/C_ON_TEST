package com.example.c_on.common;

import com.example.c_on.customer.domain.repository.CustomerRepository;
import com.example.c_on.menu.application.FoodService;
import com.example.c_on.menu.domain.repository.CategoryRepository;
import com.example.c_on.menu.domain.repository.ContainsRepository;
import com.example.c_on.menu.domain.repository.FoodRepository;
import com.example.c_on.order.application.OrderService;
import com.example.c_on.order.domain.repository.CartRepository;
import com.example.c_on.order.domain.repository.OrderDetailRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ExtendWith(DatabaseClearExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public abstract class IntegrationTest {

    @Autowired
    protected FoodRepository foodRepository;

    @Autowired
    protected CategoryRepository categoryRepository;

    @Autowired
    protected CustomerRepository customerRepository;

    @Autowired
    protected ContainsRepository containsRepository;

    @Autowired
    protected OrderDetailRepository orderDetailRepository;

    @Autowired
    protected CartRepository cartRepository;

    @Autowired
    protected FoodService foodService;

    @Autowired
    protected OrderService orderService;

    @Autowired
    protected EntityManager em;
}
