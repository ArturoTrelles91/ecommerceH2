package com.minsait.order_service.repository;

import com.minsait.order_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Aquí puedes agregar métodos personalizados si los necesitas
}