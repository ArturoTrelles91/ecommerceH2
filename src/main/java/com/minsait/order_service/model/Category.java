package com.minsait.order_service.model;

import jakarta.persistence.*;

@Entity // Le dice a JPA que esta clase es una entidad de base de datos
@Table(name = "categories") // Opcional, para nombrar la tabla explícitamente
public class Category {

    @Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento
    private Long categoryId;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    // 🔄 Constructor vacío (requerido por JPA)
    public Category() {}

    // ✅ Constructor útil
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // 🧱 Getters y Setters
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
