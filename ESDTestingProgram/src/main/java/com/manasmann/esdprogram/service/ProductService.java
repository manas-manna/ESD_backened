package com.manasmann.esdprogram.service;

import com.manasmann.esdprogram.dto.ProductRequest;
import com.manasmann.esdprogram.dto.ProductResponse;
import com.manasmann.esdprogram.entity.Product;
import com.manasmann.esdprogram.mapper.ProductMapper;
import com.manasmann.esdprogram.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepo repo;
    private final ProductMapper productMapper;



    public List<ProductResponse> retrieveAllProducts() {
        return repo.findAll().stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse retrieveProduct(String name) {
        Product product = repo.findByName(name).orElse(null);
        return product != null ? productMapper.toProductResponse(product) : null;
    }

    public String createProduct(ProductRequest request) {
        Product product = productMapper.toProduct(request);
        repo.save(product);
        return "Product created successfully";
    }

    public String updateProduct(String name, ProductRequest productDetails) {
        return repo.findByName(name)
                .map(product -> {
                    product.setPrice(productDetails.price());
                    repo.save(product);
                    ProductResponse response = productMapper.toProductResponse(product);
                    return String.format("Product updated successfully\nName: %s, Price: %.2f",
                            response.name(), response.price());
                })
                .orElse("Product not found");
    }

    public String deleteProduct(String name) {
        return repo.findByName(name)
                .map(product -> {
                    repo.delete(product);
                    return "Product deleted successfully";
                })
                .orElse("Product not found");
    }

    public List<ProductResponse> getTop2ProductsByPriceRange(double minPrice, double maxPrice) {
        List<Product> products = repo.findTop2ByPriceRange(minPrice, maxPrice);
        return products.stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}