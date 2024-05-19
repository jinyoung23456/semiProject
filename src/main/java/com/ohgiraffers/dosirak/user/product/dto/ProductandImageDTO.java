package com.ohgiraffers.dosirak.user.product.dto;

import com.ohgiraffers.dosirak.admin.product.dto.ProductImageDTO;
import com.ohgiraffers.dosirak.admin.product.dto.productDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductandImageDTO {
    private productDTO productDTO;
    private ProductImageDTO productImageDTO;

    public ProductandImageDTO() {
    }

    public ProductandImageDTO(productDTO productDTO, ProductImageDTO productImageDTO) {
        this.productDTO = productDTO;
        this.productImageDTO = productImageDTO;
    }

    // Getters and setters for productDTO and productImageDTO
}

