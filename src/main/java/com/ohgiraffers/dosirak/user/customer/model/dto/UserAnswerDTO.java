package com.ohgiraffers.dosirak.user.customer.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class UserAnswerDTO {

    private int answerCode;
    private Boolean answerStatus;
    private String answerContent;
    private String answerDate;
    private String adminId;
    private int AnswerCategory;
    private int reviewCode;
    private int askCode;

    private UserAskDTO askDTO;

}
