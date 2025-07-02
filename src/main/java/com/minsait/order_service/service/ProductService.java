package com.minsait.order_service.service;

import com.minsait.order_service.model.Category;
import com.minsait.order_service.model.Product;
import com.minsait.order_service.repository.CategoryRepository;
import com.minsait.order_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;



    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Autowired
    private CategoryRepository categoryRepository;

    public Product createProduct(Product product) {
        Long categoryId = product.getCategory().getCategoryId();
        Category category = categoryRepository.findById(categoryId).orElse(null);
        product.setCategory(category);  // asegura que tenga name y description
        return productRepository.save(product);
    }

//    public Product createProduct(Product product) {
//        return productRepository.save(product);
//    }



    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
