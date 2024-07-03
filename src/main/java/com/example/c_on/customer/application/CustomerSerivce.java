package com.example.c_on.customer.application;

import com.example.c_on.auth.dto.response.CustomerInfo;
import com.example.c_on.customer.domain.CnoProvider;
import com.example.c_on.customer.domain.Customer;
import com.example.c_on.customer.domain.repository.CustomerRepository;
import com.example.c_on.customer.exception.NoSuchCustomerException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerSerivce {

    private final CustomerRepository customerRepository;
    private final CnoProvider cnoProvider;

    public void register(String username, String password, String phoneNumber) {
        Customer customer = Customer.builder()
                .cno(cnoProvider.generateId())
                .name(username)
                .password(password)
                .phoneNumber(phoneNumber)
                .build();
        customer.addCart();
        customerRepository.save(customer);
    }

    public CustomerInfo login(String username, String password) {
        Customer customer = customerRepository.findCustomerByName(username)
                .orElseThrow(NoSuchCustomerException::new);
        customer.validatePassword(password);
        return new CustomerInfo(customer.getName(), customer.getCno());
    }
}
