package com.ohgiraffers.dosirak.user.customer.model.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserQnaDTO {

    private int qnaCode;
    private String qnaTitle;
    private String qnaAnswer;
    private String adminId;
    private int askCategoryCode;

}
