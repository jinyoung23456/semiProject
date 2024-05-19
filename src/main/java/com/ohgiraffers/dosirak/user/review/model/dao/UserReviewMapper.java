package com.ohgiraffers.dosirak.user.review.model.dao;

import com.ohgiraffers.dosirak.admin.customer.model.dto.AnswerDTO;
import com.ohgiraffers.dosirak.user.customer.common.SelectCriteria;
import com.ohgiraffers.dosirak.user.review.model.dto.DeliveryDTO;
import com.ohgiraffers.dosirak.user.review.model.dto.ReviewDTO;
import com.ohgiraffers.dosirak.user.review.model.dto.ReviewImgDTO;
import com.ohgiraffers.dosirak.user.review.model.dto.UserReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserReviewMapper {
    List<UserReviewDTO> allUserReview();

    UserReviewDTO searchUserReviewDetail(int reviewCode);

    void insertReview(UserReviewDTO review);

    UserReviewDTO searchUserReviewRegist(int reviewCode);

    AnswerDTO searchUserAnswerRegist(int reviewCode);

    List<DeliveryDTO> searchDeliveryList();

    List<ReviewDTO> getReviewListById(String userId);

    int getReviewDTOInformation(ReviewDTO reviewDTO, String userId);

    List<ReviewDTO> getMyList(String userId);

    ReviewDTO getMyReview(ReviewDTO reviewDTO);

    int EditReview(ReviewDTO reviewDTO);

    int deleteReview(int reviewCode);

    ReviewDTO findLastReview();

    /* 첨부파일 저장 */
    void insertImage(ReviewImgDTO fileInfo);

    /* 첨부파일 불러오기 */
    List<ReviewImgDTO> getImageList(int reviewCode);

    /* 페이징 추가 */
    int selectTotalCount(Map<String, String> searchMap);

    List<ReviewDTO> selectReviewList(SelectCriteria selectCriteria, String userId);

//    List<OrderDTO> reviewResultMap();
}
