package com.ohgiraffers.dosirak.user.order.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
public class OrderHistoryDTO {
    private int orderCode;
    private String orderStatus;
    private String orderRecipient;
    private String orderContact;
    private String orderAddress1;
    private String orderAddress2;
    private String orderAddress3;
    private int payPrice;
    private Date payDate;
    private String payStatus;
    private String payMethod;
    private int deliveryCode;
    private String deliveryStatus;
    private int detailCode;
    private String detailStatus;
    private int detailItemCount;
    private String productName;
    private int productPrice;
    private String firstImageName;
    private String firstImagePath;

    public OrderHistoryDTO() {
    }

    public OrderHistoryDTO(int orderCode, String orderStatus, String orderRecipient, String orderContact, String orderAddress1, String orderAddress2, String orderAddress3, int payPrice, Date payDate, String payStatus, String payMethod, int deliveryCode, String deliveryStatus, int detailCode, String detailStatus, int detailItemCount, String productName, int productPrice, String firstImageName, String firstImagePath) {
        this.orderCode = orderCode;
        this.orderStatus = orderStatus;
        this.orderRecipient = orderRecipient;
        this.orderContact = orderContact;
        this.orderAddress1 = orderAddress1;
        this.orderAddress2 = orderAddress2;
        this.orderAddress3 = orderAddress3;
        this.payPrice = payPrice;
        this.payDate = payDate;
        this.payStatus = payStatus;
        this.payMethod = payMethod;
        this.deliveryCode = deliveryCode;
        this.deliveryStatus = deliveryStatus;
        this.detailCode = detailCode;
        this.detailStatus = detailStatus;
        this.detailItemCount = detailItemCount;
        this.productName = productName;
        this.productPrice = productPrice;
        this.firstImageName = firstImageName;
        this.firstImagePath = firstImagePath;
    }
}



