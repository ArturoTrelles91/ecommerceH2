package com.minsait.order_service.model;

import jakarta.persistence.*;
import java.util.List; // nuevo

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;

    private Double price;
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /* Ahora vamos a completar la relación bidireccional desde la clase Product hacia Order.
    Esto no es estrictamente obligatorio si solo vas a navegar desde Order → Product,
    pero hacerlo te da más control,
    y es lo que se esperaría en un sistema real bien diseñado./
     */
    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

    // Getters y setters

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
