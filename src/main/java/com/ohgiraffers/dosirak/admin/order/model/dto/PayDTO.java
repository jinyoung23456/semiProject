package com.ohgiraffers.dosirak.admin.order.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class PayDTO {
    private int  payPrice;
    private LocalDate payDate;
    private String  payStatus;
    private String payMethod;
    private String orderCode;

    public PayDTO() {}

    public PayDTO(int payPrice, LocalDate payDate, String payStatus, String payMethod, String orderCode) {
        this.payPrice = payPrice;
        this.payDate = payDate;
        this.payStatus = payStatus;
        this.payMethod = payMethod;
        this.orderCode = orderCode;
    }
}
