package com.ohgiraffers.dosirak.admin.customer.model.service;

import com.ohgiraffers.dosirak.admin.customer.model.dao.CustomerMapper;
import com.ohgiraffers.dosirak.admin.customer.model.dto.*;
import com.ohgiraffers.dosirak.user.customer.model.dto.UserCustomerImgDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.*;

@Slf4j
@Service
public class CustomerService {

    private final CustomerMapper customerMapper;

    public CustomerService(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }


    /* ----- 공지사항 ----- */
    public List<NoticeDTO> findNoticeList() {

        return customerMapper.findNoticeList();
    }

    public NoticeDTO selectNoticeDetail(int noticeCode) {

        /* 게시글 상세 내용 조회 후 리턴 */
        return customerMapper.searchNoticeDetail(noticeCode);
    }

    public void writeNotice(NoticeDTO notice) {

        customerMapper.insertNotice(notice);
    }

    public void deleteNotice(int noticeCode) {

        customerMapper.deleteNotice(noticeCode);
    }

    public void updateNotice(NoticeDTO noticeTemp) {

        customerMapper.updateNotice(noticeTemp);
    }

    /* ----- 자주 묻는 질문 ----- */

    public List<QnaDTO> findQnaList() {

        return customerMapper.findQnaList();
    }

    public QnaDTO selectQnaDetail(int qnaCode) {

        /* 상세 내용 조회 후 리턴 */
        return customerMapper.searchQnaDetail(qnaCode);
    }

    public void writeQna(QnaDTO qna) {

        customerMapper.insertQna(qna);
    }

    public void deleteQna(int qnaCode) {

        customerMapper.deleteQna(qnaCode);
    }

    /* 자주 묻는 질문 수정 시, 관리자 필요에 따라 카테고리 변경 */
    public void updateQnaCategory(int qnaCode, int askCategoryCode) {

        customerMapper.updateQnaCategory(qnaCode, askCategoryCode);
    }
    public void updateQna(QnaDTO qnaTemp) {

        customerMapper.updateQna(qnaTemp);
    }


    /* ----- 1대1 문의 ----- */
    public List<AskDTO> findAskList() {

        // 1대1 문의내역 반환
        return customerMapper.findAskList();
    }

    public List<AskDTO> findAllAskList() {

        // 모든 1대1 문의내역 반환
        return customerMapper.findAllAskList();
    }


    public AskDTO selectAskDetail(int askCode) {

        /* 상담내역 상세 내용 조회 후 리턴 */
        return customerMapper.searchAskDetail(askCode);
    }

    public AnswerDTO selectAnswerDetail(int askCode) {

        /* 답변내역 상세 내용 조회 후 리턴 */
        return customerMapper.searchAnswerDetail(askCode);
    }

    public List<AskCategoryDTO> findCategoryList() {

        return customerMapper.findCategoryList();
    }

    public void writeAnswer(AnswerDTO answer) {

        /* 답변 등록 */
        customerMapper.insertAnswer(answer);
    }

    /* 답변 등록 시, 관리자 필요에 따라 카테고리 변경 */
    public void updateAskCategory(int askCode, int askCategoryCode) {

        customerMapper.updateAskCategory(askCode, askCategoryCode);
    }

    @Transactional(readOnly = true)
    public List<ImgDTO> searchImageList(int askCode) {
        return customerMapper.searchImageList(askCode);
    }


}