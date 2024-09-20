package com.eco.base.dto;

import com.eco.base.entity.ProductColor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

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
    private String brand;
    private String model;
    private String storage;
    private String releaseDate;
    private double discount;
    private Long updatedAt;
    private Long createdAt;
    private boolean isActive;
    private String imagePath;
    private MultipartFile image;
    private List<ProductColorDTO> colors;
}
