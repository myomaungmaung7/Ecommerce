package com.eco.base.mapper;

import com.eco.base.dto.ColorDTO;
import com.eco.base.dto.ProductDTO;
import com.eco.base.entity.Product;
import com.eco.base.utils.DateUtils;

import java.util.stream.Collectors;

public class ProductMapper {

    public static Product dtoToEntity(ProductDTO dto) {
        return Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .stockQuantity(dto.getStockQuantity())
                .category(dto.getCategory())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .storage(dto.getStorage())
                .releaseDate(dto.getReleaseDate())
                .discount(dto.getDiscount())
                .createdAt(DateUtils.getNowDate())
                .updatedAt(DateUtils.getNowDate())
                .isActive(true)
                .imagePath(dto.getImagePath())
                .build();
    }

    public static ProductDTO entityToDto(Product entity) {
        return ProductDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .stockQuantity(entity.getStockQuantity())
                .category(entity.getCategory())
                .brand(entity.getBrand())
                .model(entity.getModel())
                .storage(entity.getStorage())
                .releaseDate(entity.getReleaseDate())
                .discount(entity.getDiscount())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .isActive(entity.isActive())
                .imagePath(entity.getImagePath())
                .colors(entity.getProductColors().stream()
                        .map(ProductColorMapper::entityToDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
