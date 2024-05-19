package com.ohgiraffers.dosirak.admin.product.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@ToString
public class productDTO {
    private String productName;
    private int productCode;
    private int productPrice;
    private String productStatus;
    private String productSummary;
    private int productCategoryCode;
    private MultipartFile productImage;

}
