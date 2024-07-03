package com.example.c_on.order.domain.repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class StaticsticRepositoryImpl implements StaticsticRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Object[] getMostPopularFood() {
        String sql = "SELECT FOOD_NAME, SUM(QUANTITY) AS TOTAL_QUANTITY " +
                "FROM ORDER_DETAIL " +
                "WHERE ID IN ( " +
                "    SELECT ID " +
                "    FROM CART " +
                "    WHERE ORDER_DATE_TIME IS NOT NULL " +
                ") " +
                "GROUP BY FOOD_NAME " +
                "ORDER BY TOTAL_QUANTITY DESC " +
                "FETCH FIRST 1 ROWS ONLY";

        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> resultList = query.getResultList();
        return resultList.isEmpty() ? new Object[0] : resultList.get(0);
    }

    @Override
    public Object[] getMostPopularFoodByCategory() {
        String sql = "WITH Food_Sales AS ( " +
                "    SELECT OD.FOOD_NAME, SUM(OD.QUANTITY) AS TOTAL_QUANTITY " +
                "    FROM ORDER_DETAIL OD " +
                "    WHERE OD.ID IN ( " +
                "        SELECT C.ID " +
                "        FROM CART C " +
                "        WHERE C.ORDER_DATE_TIME IS NOT NULL " +
                "    ) " +
                "    GROUP BY OD.FOOD_NAME " +
                "), " +
                "Category_Sales AS ( " +
                "    SELECT C.CATEGORY_NAME, FS.FOOD_NAME, FS.TOTAL_QUANTITY " +
                "    FROM Food_Sales FS " +
                "    JOIN CONTAIN C ON FS.FOOD_NAME = C.FOOD_NAME " +
                ") " +
                "SELECT CATEGORY_NAME, FOOD_NAME, TOTAL_QUANTITY " +
                "FROM ( " +
                "    SELECT CATEGORY_NAME, FOOD_NAME, TOTAL_QUANTITY, " +
                "           ROW_NUMBER() OVER (PARTITION BY CATEGORY_NAME ORDER BY TOTAL_QUANTITY DESC) AS RN " +
                "    FROM Category_Sales " +
                ") " +
                "WHERE RN = 1";

        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> resultList = query.getResultList();
        return resultList.toArray();
    }

    @Override
    public Object[] getMostOrderedCustomer() {
        String sql = "SELECT C.CNO, C.NAME, COUNT(CART.ID) AS COMPLETED_ORDERS " +
                "FROM CART " +
                "JOIN CUSTOMER C ON CART.CNO = C.CNO " +
                "WHERE CART.ORDER_DATE_TIME IS NOT NULL " +
                "GROUP BY C.CNO, C.NAME " +
                "ORDER BY COMPLETED_ORDERS DESC " +
                "FETCH FIRST 10 ROWS ONLY";

        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> resultList = query.getResultList();
        return resultList.toArray();
    }
}
