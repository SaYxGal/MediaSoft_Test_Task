package com.task.productWarehouse;

import com.task.productWarehouse.product.model.DTO.SaveProductDTO;
import com.task.productWarehouse.product.model.Product;
import com.task.productWarehouse.product.service.ProductService;
import com.task.productWarehouse.product.service.exception.ProductNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;


@SpringBootTest
class ProductWarehouseApplicationTests {
	private static final Logger log = LoggerFactory.getLogger(ProductWarehouseApplicationTests.class);
	@Autowired
	ProductService productService;

	@Test
	void createProduct() {
		productService.deleteAll();
		SaveProductDTO dto = new SaveProductDTO();
		dto.setName("Product1");
		dto.setCategory("Category1");
		dto.setDescription("Description1");
		dto.setCount(10);
		dto.setPrice(1000.1);
		dto.setPartNumber("AB-1");
		final Product product1 = productService.createProduct(dto);
		dto.setName("Product2");
		dto.setCategory("Category2");
		dto.setDescription("Description2");
		dto.setCount(20);
		dto.setPrice(2000.2);
		dto.setPartNumber("AB-2");
		final Product product2 = productService.createProduct(dto);
		log.info("createProduct[0] " + product1.toString());
		log.info("createProduct[1] " + product2.toString());
		Assertions.assertNotNull(product1.getId());
		Assertions.assertNotNull(product2.getId());
		productService.deleteProduct(product1.getId());
		productService.deleteProduct(product2.getId());
	}
	@Test
	void readProduct(){
		productService.deleteAll();
		SaveProductDTO dto = new SaveProductDTO();
		dto.setName("Product1");
		dto.setCategory("Category1");
		dto.setDescription("Description1");
		dto.setCount(10);
		dto.setPrice(1000.1);
		dto.setPartNumber("AB-1");
		final Product product1 = productService.createProduct(dto);
		log.info("readProduct[0]: " + product1.toString());
		final Product readProduct = productService.findOne(product1.getId());
		log.info("readProduct[1]: " + readProduct.toString());
		Assertions.assertEquals(product1, readProduct);
		productService.deleteProduct(product1.getId());
	}
	@Test
	void readPageProduct(){
		productService.deleteAll();
		SaveProductDTO dto = new SaveProductDTO();
		dto.setName("Product1");
		dto.setCategory("Category1");
		dto.setDescription("Description1");
		dto.setCount(10);
		dto.setPrice(1000.1);
		dto.setPartNumber("AB-1");
		final Product product1 = productService.createProduct(dto);
		dto.setName("Product2");
		dto.setCategory("Category2");
		dto.setDescription("Description2");
		dto.setCount(20);
		dto.setPrice(2000.2);
		dto.setPartNumber("AB-2");
		final Product product2 = productService.createProduct(dto);
		Assertions.assertEquals(productService.findAll(1,5).get().toList().size(), 2);
		productService.deleteProduct(product1.getId());
		productService.deleteProduct(product2.getId());
	}
	@Test
	void updateProduct(){
		productService.deleteAll();
		SaveProductDTO dto = new SaveProductDTO();
		dto.setName("Product1");
		dto.setCategory("Category1");
		dto.setDescription("Description1");
		dto.setCount(10);
		dto.setPrice(1000.1);
		dto.setPartNumber("AB-1");
		final Product product1 = productService.createProduct(dto);
		log.info("updateProduct[0]: " + product1.toString());
		dto.setName("Product1 - Updated");
		dto.setCategory("Category1 - Updated");
		dto.setDescription("Description1 - Updated");
		dto.setCount(15);
		dto.setPrice(1500.1);
		dto.setPartNumber("AB-1  - Updated");
		final Product product2 = productService.updateProduct(product1.getId(), dto);
		log.info("updateProduct[1]: " + product2.toString());
		Assertions.assertNotEquals(product1.getLastCountChange(), product2.getLastCountChange());
		Assertions.assertNotEquals(product1.getName(), product2.getName());
		Assertions.assertEquals(product1.getId(), product2.getId());
		productService.deleteProduct(product2.getId());
	}
	@Test
	void deleteProduct(){
		productService.deleteAll();
		SaveProductDTO dto = new SaveProductDTO();
		dto.setName("Product1");
		dto.setCategory("Category1");
		dto.setDescription("Description1");
		dto.setCount(10);
		dto.setPrice(1000.1);
		dto.setPartNumber("AB-1");
		final Product product1 = productService.createProduct(dto);
		productService.deleteProduct(product1.getId());
		Assertions.assertThrows(ProductNotFoundException.class, () -> productService.findOne(product1.getId()));
	}
	@Test
	void testProductReadNotFound() {
		productService.deleteAll();
		Assertions.assertThrows(ProductNotFoundException.class, () -> productService.findOne(UUID.fromString("11111111-1111-1111-bbbb-111122223333")));
	}
}
