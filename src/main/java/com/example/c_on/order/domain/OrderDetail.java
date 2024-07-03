package com.example.c_on.order.domain;

import com.example.c_on.menu.domain.Food;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "OrderDetail")
@IdClass(OrderDetailId.class)
@Getter
public class OrderDetail {

    @Id
    @Column(name = "itemNo")
    private UUID itemNo;

    @Id
    @ManyToOne
    @JoinColumn(name = "id")
    private Cart cart;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "totalPrice")
    private Integer totalPrice;

    @ManyToOne
    @JoinColumn(name = "foodName")
    private Food food;

    @Builder
    public OrderDetail(Cart cart, Integer quantity, Integer totalPrice, Food food) {
        this.itemNo = UUID.randomUUID();
        this.cart = cart;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.food = food;
    }

    public OrderDetail() {

    }

    public void calculateTotalPrice() {
        this.totalPrice = this.quantity * food.getPrice();
    }

    public void increaseQuantity() {
       this.quantity++;
       calculateTotalPrice();
    }

    public void decreaseQuantity() {
        if (this.quantity == 1) {
            if (this.cart != null) {
                this.cart.removeOrderDetail(this);
            }
            return;
        }
        this.quantity--;
        calculateTotalPrice();
    }

    public String getFoodName() {
        return food.getFoodName();
    }
}

