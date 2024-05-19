package com.ohgiraffers.dosirak.admin.survey.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Setter
@Getter
@ToString
public class SurveyQuestionDTO {
    private int questionId;
    private int versionId;
    private int questionOrder;
    private String questionText;
    private char questionCategory;
    private List<SurveyAnswerDTO> answerList;
}
