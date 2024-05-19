package com.ohgiraffers.dosirak.admin.customer.model.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
public class NoticeDTO {

    private int noticeCode;
    private String noticeTitle;
    private String noticeContent;
    private String noticeDate;
    private String adminId;

}
