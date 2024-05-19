package com.ohgiraffers.dosirak.user.review.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeliveryDTO {

    private int orderCode;
    private int deliveryCode;
    private String deliveryStatus;
}
