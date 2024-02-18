package com.task.productWarehouse.product.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Класс, представляющий собой сущность "Товар". Именно на основании этого класса происходит создание таблицы в базе данных
 */
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, name = "part_number", unique = true)
    private String partNumber;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Integer count;
    @Column(nullable = false, name = "last_count_change")
    private LocalDateTime lastCountChange;
    @Column(nullable = false, name = "date_of_creation")
    private LocalDate dateOfCreation;

    public Product() {
    }

    public Product(String name, String partNumber, String description, String category, Double price, Integer count) {
        this.name = name;
        this.partNumber = partNumber;
        this.description = description;
        this.category = category;
        this.price = price;
        this.count = count;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public LocalDateTime getLastCountChange() {
        return lastCountChange;
    }

    public void setLastCountChange(LocalDateTime lastCountChange) {
        this.lastCountChange = lastCountChange;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(partNumber, product.partNumber) && Objects.equals(description, product.description) && Objects.equals(category, product.category) && Objects.equals(price, product.price) && Objects.equals(count, product.count) && Objects.equals(lastCountChange.toLocalDate(), product.lastCountChange.toLocalDate()) && Objects.equals(dateOfCreation, product.dateOfCreation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, partNumber, description, category, price, count, lastCountChange.toLocalDate(), dateOfCreation);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", partNumber='" + partNumber + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", lastCountChange=" + lastCountChange +
                ", dateOfCreation=" + dateOfCreation +
                '}';
    }
}
