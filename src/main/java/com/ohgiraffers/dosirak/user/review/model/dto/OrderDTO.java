package com.ohgiraffers.dosirak.user.review.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderDTO {

    private int orderCode;
    private String orderStatus;
    private String userId;
    private String orderRecipient;
    private String orderContact;
    private String orderAddress;
    private ProductDTO productCode;
    private DeliveryDTO deliveryCode;
    private List<DetailDTO> detailCode;
    private Date orderDate;


}
