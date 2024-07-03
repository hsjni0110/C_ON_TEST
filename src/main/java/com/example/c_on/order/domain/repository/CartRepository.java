package com.example.c_on.order.domain.repository;

import com.example.c_on.customer.domain.Customer;
import com.example.c_on.order.domain.Cart;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, String>{

    /**
     * Customer가 가지고 있는 Cart 중에서 OrderDateTime이 Null인 것만 가져오는 메서드
     * @param customer
     * @return 메서드가 정상 수행되면, Optional<Cart>를 반환한다.
     */
    Optional<Cart> findCartByCustomerAndOrderDateTimeIsNull(Customer customer);

    @Query("SELECT c FROM Cart c WHERE c.customer = :customer AND c.orderDateTime IS NOT NULL AND c.orderDateTime BETWEEN :startDate AND :endDate")
    List<Cart> findCartsByOrderDateRange(
            @Param("customer") Customer customer,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    List<Cart> findCartsByCustomerAndOrderDateTimeIsNotNull(Customer customer);
}
