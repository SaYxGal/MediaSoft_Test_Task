package com.task.productWarehouse.product.service.exception;

import java.util.UUID;

/**
 * Класс ошибки, возникающей при попытки получить несуществующий на складе товар.
 */
public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(UUID id) {
        super(String.format("Product with id = '%s' not found", id));
    }
}
