package com.example.c_on.common;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseCleaner {

    private final List<String> tableNames = new ArrayList<>();
    private final List<Map<String, Object>> foreignKeyConstraints = new ArrayList<>();

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    @SuppressWarnings("unchecked")
    private void findDatabaseTableNames() {
        List<String> tables = entityManager.createNativeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'").getResultList();
        tableNames.addAll(tables);
    }

    private void captureForeignKeyConstraints() {
        Session session = entityManager.unwrap(Session.class);
        session.doWork(connection -> {
            try {
                DatabaseMetaData metaData = connection.getMetaData();
                for (String tableName : tableNames) {
                    ResultSet rs = metaData.getImportedKeys(connection.getCatalog(), null, tableName);
                    while (rs.next()) {
                        Map<String, Object> constraint = new HashMap<>();
                        constraint.put("tableName", rs.getString("FKTABLE_NAME"));
                        constraint.put("constraintName", rs.getString("FK_NAME"));
                        constraint.put("columnName", rs.getString("FKCOLUMN_NAME"));
                        constraint.put("referencedTableName", rs.getString("PKTABLE_NAME"));
                        constraint.put("referencedColumnName", rs.getString("PKCOLUMN_NAME"));
                        foreignKeyConstraints.add(constraint);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Failed to capture foreign key constraints", e);
            }
        });
    }

    private void truncate() {
        captureForeignKeyConstraints();
        List<Map<String, String>> constraints = getForeignKeyConstraints();
        for (Map<String, String> constraint : constraints) {
            entityManager.createNativeQuery(String.format("ALTER TABLE %s DROP CONSTRAINT %s", constraint.get("TABLENAME"), constraint.get("CONSTRAINTNAME"))).executeUpdate();
        }

        for (String tableName : tableNames) {
            entityManager.createNativeQuery(String.format("TRUNCATE TABLE %s", tableName)).executeUpdate();
        }

        for (Map<String, Object> constraint : foreignKeyConstraints) {
            entityManager.createNativeQuery(String.format("ALTER TABLE %s ADD CONSTRAINT %s FOREIGN KEY (%s) REFERENCES %s(%s)",
                    constraint.get("tableName"), constraint.get("constraintName"), constraint.get("columnName"),
                    constraint.get("referencedTableName"), constraint.get("referencedColumnName"))).executeUpdate();
        }
    }

    private List<Map<String, String>> getForeignKeyConstraints() {
        return entityManager.createNativeQuery(
                        "SELECT CONSTRAINT_NAME AS constraintName, TABLE_NAME AS tableName " +
                                "FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS " +
                                "WHERE CONSTRAINT_TYPE = 'FOREIGN KEY'")
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