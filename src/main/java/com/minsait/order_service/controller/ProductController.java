package com.minsait.order_service.controller;

import com.minsait.order_service.model.Product;
import com.minsait.order_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import com.minsait.order_service.model.Category;
//import com.minsait.order_service.repository.CategoryRepository;
import com.minsait.order_service.repository.ProductRepository;


import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET /products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // GET /products/{id}
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }


    // POST /products
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

//    @Autowired
//    private CategoryRepository categoryRepository;

//    @PostMapping
//    public Product createProduct(@RequestBody Product product) {
//        Long categoryId = product.getCategory().getCategoryId();
//        Category category = categoryRepository.findById(categoryId).orElse(null);
//        product.setCategory(category);
//        return productRepository.save(product);
//    }

    // DELETE /products/{id}
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
