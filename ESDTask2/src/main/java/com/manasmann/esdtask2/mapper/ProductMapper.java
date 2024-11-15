package com.manasmann.esdtask2.mapper;

import com.manasmann.esdtask2.dto.ProductRequest;
import com.manasmann.esdtask2.dto.ProductResponse;
import com.manasmann.esdtask2.entity.Product;
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