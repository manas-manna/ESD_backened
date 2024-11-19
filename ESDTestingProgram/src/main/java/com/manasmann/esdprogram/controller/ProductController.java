package com.manasmann.esdprogram.controller;

import com.manasmann.esdprogram.dto.ProductRequest;
import com.manasmann.esdprogram.dto.ProductResponse;
import com.manasmann.esdprogram.entity.Product;
import com.manasmann.esdprogram.service.ProductService;
import com.manasmann.esdprogram.validation.ValidationGroups.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/top2-by-price-range")
    public ResponseEntity<List<ProductResponse>> getProductsByPriceRange(
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {
        List<ProductResponse> products = productService.getTop2ProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }


    @GetMapping("/fetch")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.retrieveAllProducts());
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("name") String name) {
        return ResponseEntity.ok(productService.retrieveProduct(name));
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody @Valid @Validated(ProductCreateGroup.class) ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteProduct(@PathVariable("name") String name) {
        return ResponseEntity.ok(productService.deleteProduct(name));
    }

    @PatchMapping("/{name}")
    public ResponseEntity<String> updateProduct(
            @PathVariable String name,
            @RequestBody @Valid @Validated(ProductUpdateGroup.class) ProductRequest updateRequest) {
        return ResponseEntity.ok(productService.updateProduct(name, updateRequest));
    }
}