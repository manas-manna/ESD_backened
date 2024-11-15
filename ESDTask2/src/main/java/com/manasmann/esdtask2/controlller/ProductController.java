package com.manasmann.esdtask2.controlller;

import com.manasmann.esdtask2.dto.ProductRequest;
import com.manasmann.esdtask2.dto.ProductResponse;
import com.manasmann.esdtask2.entity.Product;
import com.manasmann.esdtask2.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
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

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.retrieveProduct(id));
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateProduct(
            @PathVariable Long id,
            @RequestBody @Valid ProductRequest updateRequest) {
        return ResponseEntity.ok(productService.updateProduct(id, updateRequest));
    }
}