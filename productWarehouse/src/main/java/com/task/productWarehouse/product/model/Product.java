package com.task.productWarehouse.product.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Класс, представляющий собой сущность "Товар". Именно на основании этого класса происходит создание таблицы в базе данных
 */
@Table(name = "products")
@Entity(name = "products")
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    @NonNull
    private String name;
    @Column(nullable = false, name = "part_number", unique = true)
    @NonNull
    private String partNumber;
    @Column(nullable = false)
    @NonNull
    private String description;
    @Column(nullable = false)
    @NonNull
    private String category;
    @Column(nullable = false)
    @NonNull
    private Double price;
    @Column(nullable = false)
    @NonNull
    private Integer count;
    @Column(nullable = false, name = "last_count_change")
    private LocalDateTime lastCountChange;
    @Column(nullable = false, name = "date_of_creation")
    private LocalDate dateOfCreation;

    public Product(String name, String partNumber, String description, String category, Double price, Integer count) {
        this.name = name;
        this.partNumber = partNumber;
        this.description = description;
        this.category = category;
        this.price = price;
        this.count = count;
        this.lastCountChange = LocalDateTime.now();
        this.dateOfCreation = LocalDate.now();
    }
}
