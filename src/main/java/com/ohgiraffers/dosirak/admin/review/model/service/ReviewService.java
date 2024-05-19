package com.ohgiraffers.dosirak.admin.review.model.service;

import com.ohgiraffers.dosirak.admin.review.model.dao.ReviewMapper;
import com.ohgiraffers.dosirak.admin.review.model.dto.AnswerDTO;
import com.ohgiraffers.dosirak.admin.review.model.dto.ReviewDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewMapper reviewMapper) { this.reviewMapper = reviewMapper; }
    public List<ReviewDTO> allReview() {

        return reviewMapper.allReview();
    }


    public void registAnswer(AnswerDTO registAnswer) {

        reviewMapper.insertAnswer(registAnswer);
    }

    public AnswerDTO selectAnswerDetail(int answerCode) {

        return reviewMapper.searchAnswerDetail(answerCode);
    }

    public ReviewDTO getReviewInfo(int reviewCode) {

        return reviewMapper.getReviewInfo(reviewCode);
    }

    public int reviewDelete(int reviewCode) {
        return reviewMapper.reviewDelete(reviewCode);
    }

    public int reviewAnswerRegist(ReviewDTO reviewDTO) {
        return reviewMapper.reviewAnswerRegist(reviewDTO);
    }
}
