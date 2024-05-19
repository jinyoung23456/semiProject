package com.ohgiraffers.dosirak.admin.review.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class ReviewDTO {
    private int reviewCode;
    private String reviewContent;
    private String userId;
    private String  reviewEditDate;
    private String reviewDelete;
    private int detailCode;
    private String reviewDate;
    private AnswerDTO answerDTO;

    String reviewTitle;

    String productName;
    String answerContent;
    String answerDate;
    int answerCode;
    String adminId;
    boolean answerStatus;



    public ReviewDTO() {
    }

//    public ReviewDTO(Long reviewCode, String reviewContent, String reviewRecommend, String userId, LocalDateTime reviewEditDate, String reviewDelete, int detailCode, LocalDateTime reviewDate) {
//        this.reviewCode = reviewCode;
//        this.reviewContent = reviewContent;
//        this.reviewRecommend = reviewRecommend;
//        this.userId = userId;
//        this.reviewEditDate = reviewEditDate;
//        this.reviewDelete = reviewDelete;
//        this.detailCode = detailCode;
//        this.reviewDate = reviewDate;
//    }
//
//    public Long getReviewCode() {
//        return reviewCode;
//    }
//
//    public void setReviewCode(Long reviewCode) {
//        this.reviewCode = reviewCode;
//    }
//
//    public String getReviewContent() {
//        return reviewContent;
//    }
//
//    public void setReviewContent(String reviewContent) {
//        this.reviewContent = reviewContent;
//    }
//
//    public String getReviewRecommend() {
//        return reviewRecommend;
//    }
//
//    public void setReviewRecommend(String reviewRecommend) {
//        this.reviewRecommend = reviewRecommend;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public LocalDateTime getReviewEditDate() {
//        return reviewEditDate;
//    }
//
//    public void setReviewEditDate(LocalDateTime reviewEditDate) {
//        this.reviewEditDate = reviewEditDate;
//    }
//
//    public String getReviewDelete() {
//        return reviewDelete;
//    }
//
//    public void setReviewDelete(String reviewDelete) {
//        this.reviewDelete = reviewDelete;
//    }
//
//    public int getDetailCode() {
//        return detailCode;
//    }
//
//    public void setDetailCode(int detailCode) {
//        this.detailCode = detailCode;
//    }
//
//    public LocalDateTime getReviewDate() {
//        return reviewDate;
//    }
//
//    public void setReviewDate(LocalDateTime reviewDate) {
//        this.reviewDate = reviewDate;
//    }
//
//    @Override
//    public String toString() {
//        return "ReviewDTO{" +
//                "reviewCode=" + reviewCode +
//                ", reviewContent='" + reviewContent + '\'' +
//                ", reviewRecommend='" + reviewRecommend + '\'' +
//                ", userId='" + userId + '\'' +
//                ", reviewEditDate=" + reviewEditDate +
//                ", reviewDelete='" + reviewDelete + '\'' +
//                ", detailCode=" + detailCode +
//                ", reviewDate=" + reviewDate +
//                '}';
//    }
}
