package com.task.productWarehouse.product.model.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Класс, который используется для сохранения или изменения товаров в контроллере.
 * Присутствует валидация полей, реализованная соответствующими аннотациями.
 */
public class SaveProductDTO {
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotBlank(message = "partNumber is mandatory")
    private String partNumber;
    @NotBlank(message = "description is mandatory")
    private String description;
    @NotBlank(message = "category is mandatory")
    private String category;
    @NotNull(message = "price is mandatory")
    @Min(value = 1, message = "price must be greater or equal 1")
    private Double price;
    @NotNull(message = "count is mandatory")
    @Min(value = 0, message = "count must be positive number or zero")
    private Integer count;

    public SaveProductDTO() {
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
}
