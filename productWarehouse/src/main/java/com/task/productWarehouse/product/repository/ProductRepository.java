package com.task.productWarehouse.product.repository;

import com.task.productWarehouse.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
//DAO, предоставляющее методы для работы с БД
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
