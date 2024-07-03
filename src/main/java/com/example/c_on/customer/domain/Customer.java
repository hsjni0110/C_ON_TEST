package com.example.c_on.customer.domain;


import com.example.c_on.auth.exception.UnAuthorizationException;
import com.example.c_on.menu.domain.Food;
import com.example.c_on.order.domain.Cart;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name = "Customer")
public class Customer {

    @Id
    @Column(name = "cno", length = 10)
    private String cno;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "phoneno", nullable = false, length = 20)
    private String phoneNumber;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts = new ArrayList<>();

    public Customer() {}

    @Builder
    public Customer(String cno, String name, String password, String phoneNumber) {
        this.cno = cno;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public void validatePassword(String password) {
        if (!Objects.equals(this.password, password)) {
            throw new UnAuthorizationException("비밀번호가 일치하지 않습니다.");
        }
    }

    public void addCart() {
        Cart cart = new Cart(null, this, new ArrayList<>());
        carts.add(cart);
    }

    public boolean isAdmin(String cno) {
        if (Objects.equals(cno, "c0")) {
            return true;
        }
        return false;
    }

    public void addOrderDetail(Food food) {
        Cart LatestCart = this.carts.get(carts.size() - 1);
        LatestCart.addFood(food);
    }
}
