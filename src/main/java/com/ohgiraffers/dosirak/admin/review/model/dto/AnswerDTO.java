package com.ohgiraffers.dosirak.admin.review.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AnswerDTO {

    private int answerCode;
    private boolean answerStatus;
    private String answerContent;
    private LocalDateTime answerDate;
    private AdminDTO adminId;
    private int reviewCode;

    public AnswerDTO() {
    }
}
