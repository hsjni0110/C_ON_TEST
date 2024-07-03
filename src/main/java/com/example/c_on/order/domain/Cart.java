package com.example.c_on.order.domain;

import com.example.c_on.customer.domain.Customer;
import com.example.c_on.menu.domain.Food;
import com.example.c_on.order.domain.OrderDetail;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "Cart")
@Getter
public class Cart {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "orderDateTime")
    private LocalDateTime orderDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cno")
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;

    public Cart() {

    }

    @Builder
    public Cart(LocalDateTime orderDateTime, Customer customer, List<OrderDetail> orderDetails) {
        this.id = UUID.randomUUID().toString();
        this.orderDateTime = orderDateTime;
        this.customer = customer;
        this.orderDetails = orderDetails;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetails.add(orderDetail);
    }

    public void removeOrderDetail(OrderDetail orderDetail) {
        orderDetails.remove(orderDetail);
    }

    public void order() {
        this.orderDateTime = LocalDateTime.now();
    }

    public void addFood(Food food) {
        OrderDetail orderDetail = findOrderDetailByFoodName(food.getFoodName());
        if (checkOrderDetailHasFood(orderDetail)) {
            orderDetail.increaseQuantity();
        }
        createAndAddNewOrderDetail(food);
    }

    private boolean checkOrderDetailHasFood(OrderDetail orderDetail) {
        return orderDetail != null;
    }

    public OrderDetail findOrderDetailByFoodName(String foodName) {
        return orderDetails.stream()
                .filter(orderDetail -> Objects.equals(orderDetail.getFoodName(), foodName))
                .findFirst()
                .orElse(null);
    }

    private void createAndAddNewOrderDetail(Food food) {
        OrderDetail newOrderDetail = OrderDetail.builder()
                .cart(this)
                .quantity(1)
                .totalPrice(food.getPrice())
                .food(food)
                .build();
        orderDetails.add(newOrderDetail);
    }
}
