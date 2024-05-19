package com.ohgiraffers.dosirak.user.review.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DetailDTO {

    private int detailCode;
    private String detailStatus;
    private int detailItemCount;
    private int detailProductCode;
    private SuitboxDTO detailSuitboxCode;
    private OrderDTO orderCode;
    private ProductDTO productCode;
}
