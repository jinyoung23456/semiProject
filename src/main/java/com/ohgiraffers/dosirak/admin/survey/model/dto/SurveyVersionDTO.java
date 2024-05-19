package com.ohgiraffers.dosirak.admin.survey.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class SurveyVersionDTO {
    private int versionId;
    private String versionName;
    private String versionExplain;
    private Date updateDate;
    private boolean status;
}
