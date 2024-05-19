package com.ohgiraffers.dosirak.admin.survey.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
@Setter
@Getter
@ToString
public class SurveyResultDTO {
    private String userId;
    private double surveyHeight;
    private double surveyWeight;
    private double surveyBmi;
    private String surveyDiet;
    private int surveyExerciseScore;
    private int surveyCarboScore;
    private int surveyProteinScore;
    private int surveyFatScore;
    private boolean surveyDiabetes;
    private boolean surveyCancer;
    private boolean surveyKidney;
    private boolean surveyBlood;
    private Date surveyDate;
}
