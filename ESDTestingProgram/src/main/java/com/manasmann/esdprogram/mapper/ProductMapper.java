package com.manasmann.esdprogram.mapper;

import com.manasmann.esdprogram.dto.ProductRequest;
import com.manasmann.esdprogram.dto.ProductResponse;
import com.manasmann.esdprogram.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(product.getName(), product.getPrice());
    }
}