package com.ohgiraffers.dosirak.admin.order.model.dto;


import com.ohgiraffers.dosirak.admin.member.model.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class RefundDTO {

    private String refundCode;
    private int refundPrice;
    private LocalDate refundDate;
    private String refundStatus;
    private String refundReason;
    private MemberDTO member;
    private PayDTO pay;
    private String orderCode;
}
