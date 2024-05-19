package com.ohgiraffers.dosirak.user.review.model.dto;

import com.ohgiraffers.dosirak.admin.review.model.dto.AdminDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UserReviewDTO {
    private int reviewCode;
    private String reviewTitle;
    private String reviewContent;
    private String userId;
    private Date reviewEditdate; // 추후 수정
    private String reviewDelete;
    private int detailCode;
    private Date reviewDate;
    private String answerStatus;
}
