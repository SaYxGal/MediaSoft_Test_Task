package com.task.productWarehouse.product.model.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс, который используется для сохранения или изменения товаров в контроллере.
 * Присутствует валидация полей, реализованная соответствующими аннотациями.
 */
@Getter
@Setter
@NoArgsConstructor
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
}
