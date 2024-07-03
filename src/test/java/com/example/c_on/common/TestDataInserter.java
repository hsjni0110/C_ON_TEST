package com.example.c_on.common;

import com.example.c_on.customer.domain.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@AllArgsConstructor
public class TestDataInserter {

    private final CustomerRepository customerRepository;

    public TestData insertData(TestDataCreator testDataCreator) {
        TestData testData = testDataCreator.create();
        return insertData(testData);
    }

    public TestData insertData(TestData testData) {
        customerRepository.saveAll(testData.customers());
        return testData;
    }
}
