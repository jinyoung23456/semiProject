package com.ohgiraffers.dosirak.admin.survey.model.dao;

import com.ohgiraffers.dosirak.admin.survey.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SurveyAdminDAO {

    List<SurveyResultDTO> allList();

    SurveyVersionDTO getVersionByVersionId(int versionId);

    List<SurveyVersionDTO> getAllVersion();

    List<SurveyQuestionDTO> getQuestionListByVersionId(int versionId);


    int deleteVersionByVersionId(int versionId);

    void deleteQuestionByVersionId(int versionId);

    void getDeleteAnswerByVersionId(int versionId);

    void deleteRangeByVersionId(int versionId);

    List<SurveyScoreRangeDTO> getSurveyRangeByVersionId(Integer versionId);

    int updateVersionByVersionDTO(SurveyVersionDTO version);

    int updateQuestionBySurveyQuestionDTO(SurveyQuestionDTO question);


    int insertNetVersion();

    int getVersionId();

    int getQuestionIdByQuestionDTO(SurveyQuestionDTO question);


    void deleteAllAnswerByAnswer(SurveyAnswerDTO answer);

    int insertAnswerBySurveyAnswerDTO(SurveyAnswerDTO answer);
}
