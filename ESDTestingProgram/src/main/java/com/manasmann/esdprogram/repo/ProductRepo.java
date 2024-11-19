package com.manasmann.esdprogram.repo;

import com.manasmann.esdprogram.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice ORDER BY p.price DESC limit 2")
    List<Product> findTop2ByPriceRange(double minPrice, double maxPrice);

    Optional<Product> findByName(String name);
}
