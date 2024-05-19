package com.ohgiraffers.dosirak.user.review.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDTO {

    private int productCode;
    private String productName;
    private int productPrice;
    private String productStatus;
    private String procudtSummary;
    private int productCategoryCode;
}
