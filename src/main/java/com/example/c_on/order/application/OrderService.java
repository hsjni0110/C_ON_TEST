package com.example.c_on.order.application;

import com.example.c_on.auth.exception.UnAuthorizationException;
import com.example.c_on.customer.domain.Customer;
import com.example.c_on.customer.domain.repository.CustomerRepository;
import com.example.c_on.customer.exception.NoSuchCustomerException;
import com.example.c_on.menu.domain.Food;
import com.example.c_on.menu.domain.repository.FoodRepository;
import com.example.c_on.menu.dto.response.FoodResponse;
import com.example.c_on.menu.exception.NoSuchFoodException;
import com.example.c_on.order.domain.Cart;
import com.example.c_on.order.domain.OrderDetail;
import com.example.c_on.order.domain.repository.CartRepository;
import com.example.c_on.order.domain.repository.OrderDetailRepository;
import com.example.c_on.order.dto.CartInfo;
import com.example.c_on.order.dto.CartItemResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final FoodRepository foodRepository;
    private final OrderDetailRepository orderDetailRepository;

    /**
     * 회원이 음식을 장바구니에 담는 메서드
     * @param customerName
     * @param foodName
     * @return CartItemResponse를 리턴한다.
     */
    @Transactional
    public void addOrderDetail(String customerName, String foodName) {
        Customer customer = customerRepository.findCustomerByName(customerName)
                .orElseThrow(NoSuchCustomerException::new);
        Food food = foodRepository.findFoodByFoodName(foodName)
                .orElseThrow(NoSuchFoodException::new);
        customer.addOrderDetail(food);
        customerRepository.save(customer);
    }

    /**
     * customer의 장바구니 안의 음식과 그 갯수를 가져오는 메서드
     * @param customerName
     * @return List<CartItemResponse>를 리턴한다.
     */
    @Transactional(readOnly = true)
    public List<CartItemResponse> findAll(String customerName) {
        Customer customer = customerRepository.findCustomerByName(customerName)
                .orElseThrow(NoSuchCustomerException::new);
        Optional<Cart> cart = cartRepository.findCartByCustomerAndOrderDateTimeIsNull(
                customer);
        try {
            return cart.get().getOrderDetails().stream()
                    .map(CartItemResponse::of)
                    .toList();
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * 장바구니 내의 장바구니 항목의 갯수를 증가하는 메서드
     * @param customerName
     * @param foodName
     */
    @Transactional
    public void increaseCartItem(String customerName, String foodName) {
        Customer customer = customerRepository.findCustomerByName(customerName)
                .orElseThrow(NoSuchCustomerException::new);
        Optional<Cart> cart = cartRepository.findCartByCustomerAndOrderDateTimeIsNull(
                customer);
        try {
            Cart modified = cart.get();
            OrderDetail targetOrderDetail = modified.getOrderDetails()
                    .stream()
                    .filter(orderDetail -> Objects.equals(orderDetail.getFood().getFoodName(), foodName))
                    .findFirst()
                    .orElseThrow(RuntimeException::new);
            targetOrderDetail.increaseQuantity();
            cartRepository.save(modified);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * 장바구니 내의 장바구니 항목을 감소시키는 메서드
     * @param customerName
     * @param foodName
     */
    @Transactional
    public void decreaseCartItem(String customerName, String foodName) {
        Customer customer = customerRepository.findCustomerByName(customerName)
                .orElseThrow(NoSuchCustomerException::new);
        Optional<Cart> cart = cartRepository.findCartByCustomerAndOrderDateTimeIsNull(
                customer);
        try {
            Cart modified = cart.get();
            OrderDetail targetOrderDetail = modified.getOrderDetails()
                    .stream()
                    .filter(orderDetail -> Objects.equals(orderDetail.getFood().getFoodName(), foodName))
                    .findFirst()
                    .orElseThrow(RuntimeException::new);
            targetOrderDetail.decreaseQuantity();
            cartRepository.save(modified);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * 주문하는 메서드, 주문됨과 동시에 장바구니에 시간이 채워지며 새로운 장바구니가 생성된다.
     * @param customerName
     */
    @Transactional
    public void order(String customerName) {
        Customer customer = customerRepository.findCustomerByName(customerName)
                .orElseThrow(NoSuchCustomerException::new);
        Optional<Cart> cart = cartRepository.findCartByCustomerAndOrderDateTimeIsNull(
                customer);
        try {
            Cart orderedCart = cart.get();
            orderedCart.order();
            customer.addCart();
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * Customer에 따라서 이전 Cart 정보를 조회하는 메서드
     * @param customerName
     * @param startDate
     * @param endDate
     * @return
     */
    public List<CartInfo> findPreviousCartInfo(String customerName, LocalDateTime startDate, LocalDateTime endDate) {
        Customer customer = customerRepository.findCustomerByName(customerName)
                .orElseThrow(NoSuchCustomerException::new);
        List<Cart> carts = cartRepository.findCartsByOrderDateRange(customer, startDate, endDate);
        return carts.stream()
                .map(CartInfo::of)
                .toList();
    }

    public List<CartInfo> findPreviousCartInfoAll(String customerName) {
        Customer customer = customerRepository.findCustomerByName(customerName)
                .orElseThrow(NoSuchCustomerException::new);
        List<Cart> carts = cartRepository.findCartsByCustomerAndOrderDateTimeIsNotNull(customer);
        return carts.stream()
                .map(CartInfo::of)
                .toList();
    }
}
