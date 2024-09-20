package com.eco.base.service;

import com.eco.base.common.AUTHResponse;
import com.eco.base.dto.ProductDTO;
import com.eco.base.entity.Color;
import com.eco.base.entity.Product;
import com.eco.base.entity.ProductColor;
import com.eco.base.mapper.ProductColorMapper;
import com.eco.base.mapper.ProductMapper;
import com.eco.base.repository.ColorRepository;
import com.eco.base.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final ColorRepository colorRepository;

    public AUTHResponse addProduct(ProductDTO productDTO) {
        Product product = ProductMapper.dtoToEntity(productDTO);
        List<ProductColor> productColors = productDTO.getColors().stream()
                .map(colorDto -> {
                    Color color = colorRepository.findByColorName(colorDto.getColorName());
                    if (color == null) {
                        color = new Color();
                        color.setColorName(colorDto.getColorName());
                        colorRepository.save(color);
                    }
                    return ProductColorMapper.dtoToEntity(colorDto, product, color);
                })
                .collect(Collectors.toList());
        product.setProductColors(productColors);
        Product productSaved = productRepository.save(product);
        return AUTHResponse.success("Success", ProductMapper.entityToDto(productSaved));
    }
}
