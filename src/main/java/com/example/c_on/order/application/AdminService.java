package com.example.c_on.order.application;

import com.example.c_on.auth.exception.UnAuthorizationException;
import com.example.c_on.customer.domain.Customer;
import com.example.c_on.customer.domain.repository.CustomerRepository;
import com.example.c_on.customer.exception.NoSuchCustomerException;
import com.example.c_on.order.domain.repository.StaticsticRepository;
import com.example.c_on.order.dto.MostFoodByCategoryResponse;
import com.example.c_on.order.dto.MostFoodResponse;
import com.example.c_on.order.dto.TopUserResponse;
import com.example.c_on.order.exception.EmptyTargetException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService {

    private final StaticsticRepository staticsticRepository;
    private final CustomerRepository customerRepository;

    /**
     * 가장 많이 팔린 음식 조회
     * @param customerName
     * @return
     */
    public MostFoodResponse getMostSold(String customerName) {
        checkAdmin(customerName);
        Object[] mostPopularFood = staticsticRepository.getMostPopularFood();
        checkEmpty(mostPopularFood);
        String foodName = (String) mostPopularFood[0];
        BigDecimal totalQuantityDecimal = (BigDecimal) mostPopularFood[1];
        Integer totalQuantity = totalQuantityDecimal.intValue();
        return new MostFoodResponse(foodName, totalQuantity);
    }

    /**
     * 카테고리 별 가장 많이 팔린 음식 조회
     * @param customerName
     * @param categoryName
     * @return
     */
    public MostFoodByCategoryResponse getMostSoldByCategory(String customerName, String categoryName) {
        checkAdmin(customerName);
        Object[] mostPopularFoodByCategory = staticsticRepository.getMostPopularFoodByCategory();
        checkEmpty(mostPopularFoodByCategory);
        return Arrays.stream(mostPopularFoodByCategory)
                .map(obj -> (Object[]) obj)
                .filter(row -> row[0].equals(categoryName))
                .map(row -> new MostFoodByCategoryResponse(
                        (String) row[0],
                        (String) row[1],
                        ((BigDecimal) row[2]).intValue()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No data found for the given category."));
    }

    /**
     * Top 10 주문 고객 검색
     * @param customerName
     * @return
     */
    public List<TopUserResponse> getTopCustomer(String customerName) {
        checkAdmin(customerName);
        Object[] mostOrderedCustomer = staticsticRepository.getMostOrderedCustomer();
        checkEmpty(mostOrderedCustomer);
        return Arrays.stream(mostOrderedCustomer)
                .map(obj -> (Object[]) obj)
                .map(row -> new TopUserResponse(
                        (String) row[0],
                        (String) row[1],
                        ((BigDecimal) row[2]).intValue()
                ))
                .toList();
    }

    public void checkAdmin(String customerName) {
        Customer customer = customerRepository.findCustomerByName(customerName)
                .orElseThrow(NoSuchCustomerException::new);
        if (!customer.isAdmin(customer.getCno())) {
            throw new UnAuthorizationException("관리자 권한이 아닙니다.");
        }
    }

    private void checkEmpty(Object[] mostPopularFood) {
        if (mostPopularFood.length == 0) {
            throw new EmptyTargetException();
        }
    }
}
