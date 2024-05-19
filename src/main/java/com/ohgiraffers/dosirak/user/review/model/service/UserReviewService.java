package com.ohgiraffers.dosirak.user.review.model.service;

import com.ohgiraffers.dosirak.user.customer.common.Pagenation;
import com.ohgiraffers.dosirak.user.customer.common.SelectCriteria;
import com.ohgiraffers.dosirak.user.customer.model.dto.UserAskDTO;
import com.ohgiraffers.dosirak.user.customer.model.dto.UserCustomerImgDTO;
import com.ohgiraffers.dosirak.user.review.model.dao.UserReviewMapper;
import com.ohgiraffers.dosirak.user.review.model.dto.DeliveryDTO;
import com.ohgiraffers.dosirak.user.review.model.dto.ReviewDTO;
import com.ohgiraffers.dosirak.user.review.model.dto.ReviewImgDTO;
import com.ohgiraffers.dosirak.user.review.model.dto.UserReviewDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserReviewService {

    private final UserReviewMapper userReviewMapper;

    public UserReviewService(UserReviewMapper userReviewMapper) { this.userReviewMapper = userReviewMapper; }
    public List<UserReviewDTO> allUserReview() {

        return userReviewMapper.allUserReview();
    }

    public UserReviewDTO selectUserReviewDetail(int reviewCode) {

        return userReviewMapper.searchUserReviewDetail(reviewCode);
    }

    public void reviewRegist(UserReviewDTO review) {

        userReviewMapper.insertReview(review);

    }

    public List<DeliveryDTO> searchDeliveryList() {

        return userReviewMapper.searchDeliveryList();
    }

    public List<ReviewDTO> getReviewListById(String userId) {

        return userReviewMapper.getReviewListById(userId);
    }

    public int getReviewDTOInformation(ReviewDTO reviewDTO, String userId) {

        return userReviewMapper.getReviewDTOInformation(reviewDTO, userId);
    }

    public List<ReviewDTO> getMyList(String userId) {

        return userReviewMapper.getMyList(userId);
    }

    public ReviewDTO getMyReview(ReviewDTO reviewDTO) {
        return userReviewMapper.getMyReview(reviewDTO);
    }

    public int EditReview(ReviewDTO reviewDTO) {
        return userReviewMapper.EditReview(reviewDTO);
    }

    public int deleteReview(int reviewCode) {
        return userReviewMapper.deleteReview(reviewCode);
    }

    /* 가장 최근에 등록된 리뷰 조회 */
    public ReviewDTO findLastReview() {

        return userReviewMapper.findLastReview();
    }

    /* 리뷰에 첨부된 이미지 파일 저장 */
    @Transactional
    public void registImageList(List<ReviewImgDTO> imageList) {
        for(ReviewImgDTO fileInfo : imageList) {
            userReviewMapper.insertImage(fileInfo);
        }
    }

    /* 첨부파일 불러오기 */
    @Transactional
    public List<ReviewImgDTO> getImageList(int reviewCode) {

        return userReviewMapper.getImageList(reviewCode);
    }

    public Map<String, Object> selectReviewList(Map<String, String> searchMap, int page, String userId) {

        /* 1. 전체 게시글 수 확인 (검색어가 있는 경우 포함) => 페이징 처리를 위해 */
        int totalCount = userReviewMapper.selectTotalCount(searchMap);
        log.info("reviewList totalCount : {}", totalCount);

        /* 2. 페이징 처리와 연관 된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
        int limit = 10;         // 한 페이지에 보여줄 게시물의 수
        int buttonAmount = 5;   // 한 번에 보여질 페이징 버튼의 수
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
        log.info("askList selectCriteria : {}", selectCriteria);

        log.info("selectCriteria.getLimit : {}", selectCriteria.getLimit());
        log.info("selectCriteria.getOffset : {}", selectCriteria.getOffset());

        /* 3. 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
        List<ReviewDTO> reviewList = userReviewMapper.selectReviewList(selectCriteria, userId);
        log.info("reviewList : {}", reviewList);

        Map<String, Object> reviewListAndPaging = new HashMap<>();
        reviewListAndPaging.put("paging", selectCriteria);
        reviewListAndPaging.put("reviewList", reviewList);

        return reviewListAndPaging;
    }


    /* -- 주문 목록(배송, 상품 정보 포함) -- */
//    public List<OrderDTO> searchOrderList() {
//
//        /* 주문의 모든 정보 조회 뒤 반환 */
//        return userReviewMapper.reviewResultMap();
//    }
}
