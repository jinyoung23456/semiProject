package com.ohgiraffers.dosirak.user.review.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewImgDTO {

    private int imgCode;
    private int refReviewCode;
    private String originalName;
    private String savedName;
    private String savePath;
    private String fileType;
    private String thumbNailPath;
    private String attachmentStatus;

    private ReviewDTO reviewDTO;

}
