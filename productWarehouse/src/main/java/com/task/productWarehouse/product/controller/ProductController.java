package com.task.productWarehouse.product.controller;

import com.task.productWarehouse.product.model.DTO.SaveProductDTO;
import com.task.productWarehouse.product.model.DTO.ViewProductDTO;
import com.task.productWarehouse.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

@Tag(name = "Product", description = "Product management APIs")
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @Operation(
            summary = "Retrieve a Product by UUID",
            description = "Get a Product object by specifying its UUID. The response is Product object with full information about it.")
    @GetMapping("/{id}")
    public ViewProductDTO read(@PathVariable UUID id){
        return new ViewProductDTO(productService.findOne(id));
    }
    @Operation(
            summary = "Retrieve a page of Product objects",
            description = "Get a Product page by passing number of page and its size. The response is page of Product objects.")
    @GetMapping
    public ResponseEntity<Map<String,Object>> readAll(@RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "5") int size){
        try{
            Page<ViewProductDTO> products = productService.findAll(page,size).map(ViewProductDTO::new);
            Map<String, Object> response = new HashMap<>();
            response.put("products", products.get().toList());
            response.put("currentPage", products.getNumber());
            response.put("totalItems", products.getTotalElements());
            response.put("totalPages", products.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(
            summary = "Create a new Product object",
            description = "Create a Product object by passing SaveProductDTO schema. The response is created Product object.")
    @PostMapping
    public ViewProductDTO create(@Valid @RequestBody SaveProductDTO productDTO){
        return new ViewProductDTO(productService.createProduct(productDTO));
    }
    @Operation(
            summary = "Update existing Product object",
            description = "Update a Product object by passing SaveProductDTO schema with new fields values and specifying its UUID. The response is updated Product object.")
    @PutMapping("/{id}")
    public ViewProductDTO update(@PathVariable UUID id, @Valid @RequestBody SaveProductDTO productDTO){
        return new ViewProductDTO(productService.updateProduct(id, productDTO));
    }
    @Operation(
            summary = "Delete existing Product object",
            description = "Delete a Product object by specifying its UUID. The response is deleted Product object.")
    @DeleteMapping("/{id}")
    public ViewProductDTO delete(@PathVariable UUID id){
        return new ViewProductDTO(productService.deleteProduct(id));
    }
}
