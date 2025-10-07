package com.ytechtrade.inventorymanagementsystem.specification;

import com.ytechtrade.inventorymanagementsystem.models.*;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

// TODO Using SQL to filter in database
//Specification is used in Filtering data in a database
@Slf4j
public class TransactionFilter {

    public static Specification<Transaction> byFilter(String searchValue) {
        return (root, query, criteriaBuilder) -> {
            // If filter is null or empty, return true for all transactions
            if (searchValue == null || searchValue.isEmpty()) {
                return criteriaBuilder.conjunction(); // Always true
            }

            String searchPattern = "%" + searchValue.toLowerCase() + "%";

            // Create a list to hold all predicates
            List<Predicate> predicates = new ArrayList<>();

            log.debug("SEARCH VALUE in byFilter IS: " + searchPattern);
            // Check transactions fields
            // 主表字段
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("note")), searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("status").as(String.class)), searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("transactionType").as(String.class)), searchPattern));

            // Safely join and check user fields using LEFT JOIN
            if (root.getJoins().stream().noneMatch(j -> j.getAttribute().getName().equals("user"))) {
                // 关联表字段 - 使用已存在的join，避免重复join
                Join<Transaction, User> userJoin = root.join("user", JoinType.LEFT);
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(userJoin.get("name")), searchPattern));
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(userJoin.get("email")), searchPattern));
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(userJoin.get("phoneNumber")), searchPattern));
            }


            // Safely join and check supplier fields using LEFT JOIN
            if (root.getJoins().stream().noneMatch(j -> j.getAttribute().getName().equals("supplier"))) {
                Join<Transaction, Supplier> supplierJoin = root.join("supplier", JoinType.LEFT);
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(supplierJoin.get("name")), searchPattern));
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(supplierJoin.get("contactInfo")), searchPattern));
            }

            // Safely join and check product fields using LEFT JOIN
            if (root.getJoins().stream().noneMatch(j -> j.getAttribute().getName().equals("product"))) {
                Join<Transaction, Product> productJoin = root.join("product", JoinType.LEFT);
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(productJoin.get("name")), searchPattern));
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(productJoin.get("sku")), searchPattern));
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(productJoin.get("description")), searchPattern));

                // Safely join product category using LEFT JOIN
                if (productJoin.getJoins().stream().noneMatch(j -> j.getAttribute().getName().equals("category"))) {
                    Join<Product, Category> categoryJoin = productJoin.join("category", JoinType.LEFT);
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(categoryJoin.get("name")), searchPattern));
                }
            }

            // Combine all predicates with OR
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }


    // New method for filtering transactions by month and year
    public static Specification<Transaction> byMonthAndYear(int month, int year) {
        return (root, query, criteriaBuilder) -> {
            // Use the month and year functions on the createdAt date field
            Expression<Integer> monthExpression = criteriaBuilder.function("month", Integer.class, root.get("createdAt"));
            Expression<Integer> yearExpression = criteriaBuilder.function("year", Integer.class, root.get("createdAt"));

            // Create predicates for the month and year
            Predicate monthPredicate = criteriaBuilder.equal(monthExpression, month);
            Predicate yearPredicate = criteriaBuilder.equal(yearExpression, year);

            // Combine the month and year predicates
            return criteriaBuilder.and(monthPredicate, yearPredicate);
        };
    }
}
