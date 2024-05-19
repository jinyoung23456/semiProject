package com.ohgiraffers.dosirak.user.order.model.dto;

import com.ohgiraffers.dosirak.user.suitBox.model.dto.SuitBoxDTO;
import com.ohgiraffers.dosirak.user.suitBox.model.dto.SuitBoxMenuDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartDTO {

    private String orderCode;
    private int cartitemCount;
    private int productCode;
    private int suitboxCode;
    private String productName;
    private int productPrice;
    private String savePath;
    private SuitBoxDTO suitbox;
    private SuitBoxMenuDTO detailSuitBox;

    public CartDTO() {
    }

    public CartDTO(String orderCode, int cartitemCount, int productCode, String productName, int productPrice, String savePath, SuitBoxDTO suitbox, SuitBoxMenuDTO detailSuitBox) {
        this.orderCode = orderCode;
        this.cartitemCount = cartitemCount;
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
        this.savePath = savePath;
        this.suitbox = suitbox;
        this.detailSuitBox = detailSuitBox;
    }
}
