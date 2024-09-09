package com.eco.base.mapper;

import com.eco.base.dto.ProductDTO;
import com.eco.base.entity.Product;

public class ProductMapper {

    public static Product entityToDto(ProductDTO dto) {
        return Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .stockQuantity(dto.getStockQuantity())
                .category(dto.getCategory())
                .build();
    }

    public static ProductDTO dtoToEntity(Product entity) {
        return ProductDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .stockQuantity(entity.getStockQuantity())
                .category(entity.getCategory())
                .build();
    }
}
