package com.ohgiraffers.dosirak.admin.customer.model.dao;

import com.ohgiraffers.dosirak.admin.customer.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CustomerMapper {


    /* ----- 공지사항 ----- */
    List<NoticeDTO> findNoticeList();
    NoticeDTO searchNoticeDetail(int noticeCode);
    void insertNotice(NoticeDTO notice);
    void deleteNotice(int noticeCode);
    void updateNotice(NoticeDTO noticeTemp);


    /* ----- 자주 묻는 질문 ----- */
    List<QnaDTO> findQnaList();
    QnaDTO searchQnaDetail(int qnaCode);
    void insertQna(QnaDTO qna);
    void deleteQna(int qnaCode);
    void updateQna(QnaDTO qnaTemp);
    void updateQnaCategory(int qnaCode, int askCategoryCode);    // 카테고리 항목 변경



    /* ----- 1대1 문의 ----- */
    List<AskDTO> findAskList();     // 특정 문의
    List<AskDTO> findAllAskList();  // 모든 문의
    AskDTO searchAskDetail(int askCode);
    AnswerDTO searchAnswerDetail(int askCode);
    List<AskCategoryDTO> findCategoryList();

    /* ----- 답변 등록 ----- */
    void updateAskCategory(int askCode, int askCategoryCode);    // 카테고리 항목 변경
    void insertAnswer(AnswerDTO answer);            // 답변 등록

    /* --- 이미지 추가 --- */
    List<ImgDTO> searchImageList(int askCode);



}
