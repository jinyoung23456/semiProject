package com.ohgiraffers.dosirak.admin.customer.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class QnaDTO {

    private int qnaCode;
    private String qnaTitle;
    private String qnaAnswer;
    private String adminId;
    private int askCategoryCode;
    private int askCode;
    private AskCategoryDTO askCategoryDTO;

    public QnaDTO() {};

}
