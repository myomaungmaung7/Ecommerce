package com.eco.admin;

import com.eco.base.common.AUTHResponse;
import com.eco.base.dto.ProductDTO;
import com.eco.base.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "PRODUCT-CONTROLLER")
public class ProductController {

    private final ProductService productService;

    @PostMapping("add-product")
    public ResponseEntity<AUTHResponse> addProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.addProduct(productDTO));
    }
}
