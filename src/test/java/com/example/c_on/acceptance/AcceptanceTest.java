package com.example.c_on.acceptance;

import com.example.c_on.common.DatabaseCleaner;
import com.example.c_on.common.DatabaseClearExtension;
import com.example.c_on.common.TestData;
import com.example.c_on.common.TestDataInserter;
import com.example.c_on.customer.domain.repository.CustomerRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

@ExtendWith(DatabaseClearExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AcceptanceTest {

    @Autowired
    protected CustomerRepository customerRepository;

    @Autowired
    protected TestDataInserter testDataInserter;

    protected final TestData testData = new TestData();

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    protected void 초기_데이터_저장() {
        testDataInserter.insertData(testData);
    }

}
