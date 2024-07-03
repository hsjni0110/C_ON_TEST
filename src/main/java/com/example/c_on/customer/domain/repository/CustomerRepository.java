package com.example.c_on.customer.domain.repository;

import com.example.c_on.customer.domain.Customer;
import com.example.c_on.customer.exception.NoSuchCustomerException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findCustomerByName(String username);
}
