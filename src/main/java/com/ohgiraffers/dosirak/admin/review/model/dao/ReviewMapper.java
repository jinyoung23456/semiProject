package com.ohgiraffers.dosirak.admin.review.model.dao;

import com.ohgiraffers.dosirak.admin.review.model.dto.AnswerDTO;
import com.ohgiraffers.dosirak.admin.review.model.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<ReviewDTO> allReview();


    void insertAnswer(AnswerDTO registAnswer);

    AnswerDTO searchAnswerDetail(int answerCode);

    ReviewDTO getReviewInfo(int reviewCode);

    int reviewDelete(int reviewCode);

    int reviewAnswerRegist(ReviewDTO reviewDTO);
}
