package com.eco.base.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private String category;
}
