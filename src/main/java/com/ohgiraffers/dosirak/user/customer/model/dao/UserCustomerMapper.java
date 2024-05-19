package com.ohgiraffers.dosirak.user.customer.model.dao;

import com.ohgiraffers.dosirak.user.customer.common.SelectCriteria;
import com.ohgiraffers.dosirak.user.customer.model.dto.*;
import com.ohgiraffers.dosirak.user.review.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserCustomerMapper {


    /* ----- 공지사항 ----- */
//    List<UserNoticeDTO> findNoticeList();
    List<UserNoticeDTO> selectNoticeList(SelectCriteria selectCriteria);
    UserNoticeDTO searchNoticeDetail(int noticeCode);


    /* ----- 자주 묻는 질문 ----- */
    List<UserQnaDTO> findQnaList();
    UserQnaDTO searchQnaDetail(int qnaCode);
    List<UserAskCategoryDTO> findCategoryList();


    /* ----- 1대1 문의 ----- */

    /* -- 모든 문의 목록 조회(only view) -- */
    UserAskDTO findAskList(int askCode);

    /* -- 모든 문의 글 목록/정보 조회 및 반환 --*/
    List<UserAskDTO> selectAskList(SelectCriteria selectCriteria);

    /* -- 특정 문의 조회 (detail) -- */
    UserAskDTO searchAskDetail(int askCode);
    UserAnswerDTO searchAnswerDetail(int askCode);
    void insertAttachment(UserCustomerImgDTO userCustomerImgDTO);
    void insertAsk(UserAskDTO newAsk);
    void insertImage(UserCustomerImgDTO fileInfo);

    /* -- 페이징 추가 --- */
    int selectTotalCountNotice(Map<String, String> searchMap);
    int selectTotalCountAsk(Map<String, String> searchMap);

    /* -- 가장 최신 문의 조회 -- */
    UserAskDTO searchLastAsk();

    /* -- 이미지 리스트 조회 -- */
    List<UserCustomerImgDTO> searchImageList(int askCode);

    /* -- 문의 수정 -- */
    void updateAsk(UserAskDTO askTemp);

    /* -- 이미지 삭제 -- */
    void deleteImg(int askCode);

    /* -- 문의 삭제 (이미지 삭제 후 실행) -- */
    void deleteAsk(int askCode);


}

