package com.task.productWarehouse.product.service;

import com.task.productWarehouse.product.model.DTO.SaveProductDTO;
import com.task.productWarehouse.product.model.Product;
import com.task.productWarehouse.product.repository.ProductRepository;
import com.task.productWarehouse.product.service.exception.ProductNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Transactional(readOnly = true)
    public Product findOne(UUID id){
        final Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ProductNotFoundException(id));
    }
    @Transactional(readOnly = true)
    public Page<Product> findAll(int page, int size){
        return productRepository.findAll(PageRequest.of(page - 1, size, Sort.by("id").descending()));
    }
    @Transactional
    public Product createProduct(SaveProductDTO productDTO){
        final Product product = new Product(productDTO.getName(), productDTO.getPartNumber(),
                productDTO.getDescription(), productDTO.getCategory(), productDTO.getPrice(),
                productDTO.getCount());
        product.setLastCountChange(LocalDateTime.now());
        product.setDateOfCreation(LocalDate.now());
        return productRepository.save(product);
    }
    @Transactional
    public Product updateProduct(UUID id, SaveProductDTO updateProductDTO){
        final Product currentProduct = findOne(id);
        currentProduct.setName(updateProductDTO.getName());
        currentProduct.setPartNumber(updateProductDTO.getPartNumber());
        currentProduct.setDescription(updateProductDTO.getDescription());
        currentProduct.setCategory(updateProductDTO.getCategory());
        currentProduct.setPrice(updateProductDTO.getPrice());
        if(!Objects.equals(currentProduct.getCount(), updateProductDTO.getCount())){
            currentProduct.setCount(updateProductDTO.getCount());
            currentProduct.setLastCountChange(LocalDateTime.now());
        }
        return productRepository.save(currentProduct);
    }
    @Transactional
    public Product deleteProduct(UUID id){
        final Product currentProduct = findOne(id);
        productRepository.delete(currentProduct);
        return currentProduct;
    }
}
