package com.ohgiraffers.dosirak.user.product.dto;

import com.ohgiraffers.dosirak.admin.product.dto.ProductImageDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class ProductUserDTO {
    private String productName;
    private int productCode;
    private int productPrice;
    private String productStatus;
    private String productSummary;
    private int productCategoryCode;
    @Setter
    @Getter
    private List<ProductImageDTO> imageList; // 이미지 리스트를 담는 속성

    public ProductUserDTO() {
    }

    public ProductUserDTO(String productName, int productCode, int productPrice, String productStatus, String productSummary, int productCategoryCode, List<ProductImageDTO> imageList) {
        this.productName = productName;
        this.productCode = productCode;
        this.productPrice = productPrice;
        this.productStatus = productStatus;
        this.productSummary = productSummary;
        this.productCategoryCode = productCategoryCode;
        this.imageList = imageList;
    }
}
