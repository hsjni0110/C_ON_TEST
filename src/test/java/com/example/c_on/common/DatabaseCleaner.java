package com.example.c_on.common;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseCleaner {

    private final List<String> tableNames = new ArrayList<>();

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    @SuppressWarnings("unchecked")
    private void findDatabaseTableNames() {
        List<String> tables = entityManager.createNativeQuery("SELECT table_name FROM user_tables").getResultList();
        tableNames.addAll(tables);
    }

    private void truncate() {
        List<Map<String, String>> constraints = getForeignKeyConstraints();
        for (Map<String, String> constraint : constraints) {
            entityManager.createNativeQuery(String.format("ALTER TABLE %s DISABLE CONSTRAINT %s", constraint.get("TABLENAME"), constraint.get("CONSTRAINTNAME"))).executeUpdate();
        }

        for (String tableName : tableNames) {
            entityManager.createNativeQuery(String.format("TRUNCATE TABLE %s", tableName)).executeUpdate();
        }

        for (Map<String, String> constraint : constraints) {
            entityManager.createNativeQuery(String.format("ALTER TABLE %s ENABLE CONSTRAINT %s", constraint.get("TABLENAME"), constraint.get("CONSTRAINTNAME"))).executeUpdate();
        }
    }

    private List<Map<String, String>> getForeignKeyConstraints() {
        return entityManager.createNativeQuery(
                        "SELECT uc.constraint_name AS constraintName, uc.table_name AS tableName " +
                                "FROM user_constraints uc " +
                                "WHERE uc.constraint_type = 'R'")
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .getResultList();
    }

    @Transactional
    public void clear() {
        entityManager.clear();
        truncate();
    }
}