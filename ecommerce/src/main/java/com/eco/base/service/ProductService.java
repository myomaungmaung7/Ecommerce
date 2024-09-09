package com.eco.base.service;

import com.eco.base.common.AUTHResponse;
import com.eco.base.common.Constant;
import com.eco.base.dto.ProductDTO;
import com.eco.base.entity.Product;
import com.eco.base.mapper.ProductMapper;
import com.eco.base.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public AUTHResponse addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product = ProductMapper.entityToDto(productDTO);
        product = productRepository.save(product);

        return AUTHResponse.success("Success", ProductMapper.dtoToEntity(product));
    }
}
