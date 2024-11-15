package com.manasmann.esdtask2.service;


import com.manasmann.esdtask2.dto.ProductRequest;
import com.manasmann.esdtask2.dto.ProductResponse;
import com.manasmann.esdtask2.entity.Product;
import com.manasmann.esdtask2.mapper.ProductMapper;
import com.manasmann.esdtask2.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepo repo;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepo repo, ProductMapper productMapper) {
        this.repo = repo;
        this.productMapper = productMapper;
    }

    public List<ProductResponse> retrieveAllProducts() {
        return repo.findAll().stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse retrieveProduct(Long id) {
        Product product = repo.findById(id).orElse(null);
        return product != null ? productMapper.toProductResponse(product) : null;
    }

    public String createProduct(ProductRequest request) {
        Product product = productMapper.toProduct(request);
        repo.save(product);
        return "Product created successfully";
    }

    public String updateProduct(Long id, ProductRequest productDetails) {
        Product product = repo.findById(id).orElse(null);
        if (product != null) {
            product.setName(productDetails.name());
            product.setPrice(productDetails.price());
            repo.save(product);
            return "Product updated successfully";
        }
        return "Product not found";
    }

    public String deleteProduct(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Product deleted successfully";
        }
        return "Product not found";
    }

    public List<ProductResponse> getTop2ProductsByPriceRange(double minPrice, double maxPrice) {
        List<Product> products = repo.findTop2ByPriceRange(minPrice, maxPrice);
        return products.stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}