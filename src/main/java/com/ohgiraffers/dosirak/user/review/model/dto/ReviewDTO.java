package com.ohgiraffers.dosirak.user.review.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
public class ReviewDTO {

    int productCode;

    String productName;

    /* PAY 테이블에서 가져오는 PAY_DATE컬럼 */
    Date orderDate;

    String deliveryStatus;

    int detailCode;

    String reviewTitle;
    String reviewContent;

    Date reviewDate;
    String answerStatus;

    String answerContent;

    int reviewCode;

    public ReviewDTO () {}

}
