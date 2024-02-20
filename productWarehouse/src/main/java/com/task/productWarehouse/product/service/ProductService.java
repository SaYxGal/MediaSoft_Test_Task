package com.task.productWarehouse.product.service;

import com.task.productWarehouse.product.model.DTO.SaveProductDTO;
import com.task.productWarehouse.product.model.Product;
import com.task.productWarehouse.product.repository.ProductRepository;
import com.task.productWarehouse.product.service.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
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


/**
 * Класс, содержащий методы, реализующие бизнес-логику приложения по работе с товарами
 */
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    /**
     * @param id Уникальный идентификатор товара формата UUID
     * @return Найденный в базе данных объект
     * @throws ProductNotFoundException Товар на складе с данным идентификатором не найден
     */
    @Transactional(readOnly = true)
    public Product findOne(UUID id){
        final Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ProductNotFoundException(id));
    }

    /**
     * @param page Номер страницы товаров
     * @param size Размер страницы товаров
     * @return Страница товаров указанного размера
     */
    @Transactional(readOnly = true)
    public Page<Product> findAll(int page, int size){
        return productRepository.findAll(PageRequest.of(page - 1, size, Sort.by("id").descending()));
    }

    /**
     * @param productDTO Класс, содержащий информацию, необходимую для сохранения товара в БД
     * @return Товар, который был создан данным методом
     */
    @Transactional
    public Product createProduct(SaveProductDTO productDTO){
        final Product product = new Product(productDTO.getName(), productDTO.getPartNumber(),
                productDTO.getDescription(), productDTO.getCategory(), productDTO.getPrice(),
                productDTO.getCount());
        return productRepository.save(product);
    }

    /**
     * @param id Уникальный идентификатор товара, подтвергающегося изменению
     * @param updateProductDTO Класс, содержащий новую информацию о выбранном товаре
     * @return Изменённый товар
     */
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

    /**
     * @param id Уникальный идентификатор товара, который подлежит удалению
     * @return Удалённый товар
     */
    @Transactional
    public Product deleteProduct(UUID id){
        final Product currentProduct = findOne(id);
        productRepository.delete(currentProduct);
        return currentProduct;
    }

    //Удаление всего товара (в контроллере не используется)
    @Transactional
    public void deleteAll(){
        productRepository.deleteAll();
    }
}
