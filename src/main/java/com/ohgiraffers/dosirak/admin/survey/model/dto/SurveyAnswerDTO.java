package com.ohgiraffers.dosirak.admin.survey.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SurveyAnswerDTO {
    private int questionId;
    private int answerId;
    private String answerText;
    private int answerValue;
}
