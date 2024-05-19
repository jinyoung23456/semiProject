package com.ohgiraffers.dosirak.admin.order.model.dto;

import com.ohgiraffers.dosirak.admin.member.model.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderDTO {

    private String orderCode;
    private String orderStatus;
    private String orderRecipient;
    private String orderContact;
    private String orderAddress1;
    private String orderAddress2;
    private String orderAddress3;
    private PayDTO pay;
    private MemberDTO member;
    private RefundDTO refund;
    private DeliveryDTO delivery;
    private List<DetailDTO> detail;

    public OrderDTO() {}

    public OrderDTO(String orderCode, String orderStatus, String orderRecipient, String orderContact, String orderAddress1, String orderAddress2, String orderAddress3, PayDTO pay, MemberDTO member, RefundDTO refund, DeliveryDTO delivery, List<DetailDTO> detail) {
        this.orderCode = orderCode;
        this.orderStatus = orderStatus;
        this.orderRecipient = orderRecipient;
        this.orderContact = orderContact;
        this.orderAddress1 = orderAddress1;
        this.orderAddress2 = orderAddress2;
        this.orderAddress3 = orderAddress3;
        this.pay = pay;
        this.member = member;
        this.refund = refund;
        this.delivery = delivery;
        this.detail = detail;
    }
}
