package com.task.productWarehouse.product.repository;

import com.task.productWarehouse.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Интерфейс, выступающий в качестве DAO. Предоставляет методы для работы с базой данных.
 */
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
