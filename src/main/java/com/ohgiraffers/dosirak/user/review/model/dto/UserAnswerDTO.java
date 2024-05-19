package com.ohgiraffers.dosirak.user.review.model.dto;

import com.ohgiraffers.dosirak.admin.review.model.dto.AdminDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UserAnswerDTO {

    private int answerCode;
    private boolean answerStatus;
    private String answerContent;
    private LocalDate answerDate;
    private AdminDTO adminId;
    private int reviewCode;
}
