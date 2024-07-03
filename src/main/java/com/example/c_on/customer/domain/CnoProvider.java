package com.example.c_on.customer.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CnoProvider {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public String generateId() {
        String prefix = "c";
        Long maxId = entityManager.createQuery("SELECT COALESCE(MAX(CAST(SUBSTRING(e.cno, 2) AS long)), 0) FROM Customer e", Long.class)
                .getSingleResult();
        return prefix + (maxId + 1);
    }
}
