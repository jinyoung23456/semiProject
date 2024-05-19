package com.ohgiraffers.dosirak.admin.order.model.dto;

import com.ohgiraffers.dosirak.admin.member.model.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeliveryDTO {

    String deliveryCode;
    String deliveryStatus;
    OrderDTO order;
    MemberDTO member;

    public DeliveryDTO() {}

    public DeliveryDTO(String deliveryCode, String deliveryStatus, OrderDTO order, MemberDTO member) {
        this.deliveryCode = deliveryCode;
        this.deliveryStatus = deliveryStatus;
        this.order = order;
        this.member = member;
    }
}
