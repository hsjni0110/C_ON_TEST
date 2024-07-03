package com.example.c_on.order.domain.repository;

import com.example.c_on.menu.domain.Food;
import com.example.c_on.order.domain.OrderDetail;
import com.example.c_on.order.domain.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {

    OrderDetail findOrderDetailByFood(Food food);
}
