package com.task.productWarehouse.product.service;

import com.task.productWarehouse.product.model.DTO.SaveProductDTO;
import com.task.productWarehouse.product.model.Product;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IProductService {
    public Product findOne(UUID id);
    public Page<Product> findAll(int page, int size);
    public Product createProduct(SaveProductDTO productDTO);
    public Product updateProduct(UUID id, SaveProductDTO updateProductDTO);
    public Product deleteProduct(UUID id);
    public void deleteAll();
}
