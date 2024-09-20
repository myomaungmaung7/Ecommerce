package com.eco.base.mapper;

import com.eco.base.dto.ProductColorDTO;
import com.eco.base.entity.Color;
import com.eco.base.entity.Product;
import com.eco.base.entity.ProductColor;

public class ProductColorMapper {

    public static ProductColorDTO entityToDto(ProductColor entity) {
        if (entity == null) {
            return null;
        }
        return ProductColorDTO.builder()
                .colorName(entity.getColor().getColorName())
                .availableStock(entity.getAvailableStock())
                .build();
    }

    public static ProductColor dtoToEntity(ProductColorDTO dto, Product product, Color color) {
        if (dto == null || product == null || color == null) {
            return null;
        }
        return ProductColor.builder()
                .product(product)
                .color(color)
                .availableStock(dto.getAvailableStock())
                .build();
    }
}
