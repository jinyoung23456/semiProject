package com.ohgiraffers.dosirak.admin.customer.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class AnswerDTO {

    private int answerCode;
    private boolean answerStatus;
    private String answerContent;
    private Date answerDate;
    private String adminId;
    private int askCategoryCode;
    private int askCode; //askCode

    private AskCategoryDTO askCategoryDTO;
    private AskDTO askDTO;

}
