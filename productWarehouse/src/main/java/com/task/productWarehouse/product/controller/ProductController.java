package com.task.productWarehouse.product.controller;

import com.task.productWarehouse.product.model.DTO.SaveProductDTO;
import com.task.productWarehouse.product.model.DTO.ViewProductDTO;
import com.task.productWarehouse.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public ViewProductDTO read(@PathVariable UUID id){
        return new ViewProductDTO(productService.findOne(id));
    }
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
    @PostMapping
    public ViewProductDTO create(@Valid @RequestBody SaveProductDTO productDTO){
        return new ViewProductDTO(productService.createProduct(productDTO));
    }
    @PutMapping("/{id}")
    public ViewProductDTO update(@PathVariable UUID id, @Valid @RequestBody SaveProductDTO productDTO){
        return new ViewProductDTO(productService.updateProduct(id, productDTO));
    }
    @DeleteMapping("/{id}")
    public ViewProductDTO delete(@PathVariable UUID id){
        return new ViewProductDTO(productService.deleteProduct(id));
    }
}
