package com.task.productWarehouse.product.model.DTO;

import com.task.productWarehouse.product.model.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Класс, который используется в качестве возвращаемого значения в контроллере.
 * Нужен для удобного скрытия, дополнения данных, содержащихся в сущности "Товар"
 */
public class ViewProductDTO {
    private final UUID id;
    private final String name;
    private final String partNumber;
    private final String description;
    private final String category;
    private final Double price;
    private final Integer count;
    private final LocalDateTime lastCountChange;
    private final LocalDate dateOfCreation;

    public ViewProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.partNumber = product.getPartNumber();
        this.description = product.getDescription();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.count = product.getCount();
        this.lastCountChange = product.getLastCountChange();
        this.dateOfCreation = product.getDateOfCreation();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getCount() {
        return count;
    }

    public LocalDateTime getLastCountChange() {
        return lastCountChange;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }
}
