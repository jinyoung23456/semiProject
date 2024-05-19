package com.ohgiraffers.dosirak.admin.survey.model.service;

import com.ohgiraffers.dosirak.admin.survey.model.dao.SurveyAdminDAO;
import com.ohgiraffers.dosirak.admin.survey.model.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SurveyAdminService {
    private final SurveyAdminDAO surveyMapper;

    public SurveyAdminService(SurveyAdminDAO surveyMapper) {
        this.surveyMapper = surveyMapper;
    }

    public List<SurveyResultDTO> surveyList(){
        return surveyMapper.allList();
    }

    public SurveyVersionDTO getVersionByVersionId(int versionId) {
        return surveyMapper.getVersionByVersionId(versionId);
    }

    public List<SurveyVersionDTO> getAllVersion() {
        return surveyMapper.getAllVersion();
    }

    public List<SurveyQuestionDTO> getQuestionListByVersionId(int versionId) {
        return surveyMapper.getQuestionListByVersionId(versionId);
    }

    public int deleteVersionByVersionId(int versionId) {
        surveyMapper.getDeleteAnswerByVersionId(versionId);
        surveyMapper.deleteQuestionByVersionId(versionId);
        surveyMapper.deleteRangeByVersionId(versionId);
        int result = surveyMapper.deleteVersionByVersionId(versionId);
        return result;
    }

    public List<SurveyScoreRangeDTO> getSurveyRangeByVersionId(Integer versionId) {
        return surveyMapper.getSurveyRangeByVersionId(versionId);
    }

    public int updateVersionByVersionDTO(SurveyVersionDTO version) {
        return surveyMapper.updateVersionByVersionDTO(version);
    }

    public int updateQuestionBySurveyQuestionDTO(SurveyQuestionDTO question) {
        return surveyMapper.updateQuestionBySurveyQuestionDTO(question);
    }



    public int insertNetVersion() {
        return surveyMapper.insertNetVersion();
    }

    public int getVersionId() {
        return surveyMapper.getVersionId();
    }

    public int getQuestionIdByQuestionDTO(SurveyQuestionDTO question) {
        return surveyMapper.getQuestionIdByQuestionDTO(question);
    }



    public void deleteAllAnswerByAnswer(SurveyAnswerDTO answer) {
        surveyMapper.deleteAllAnswerByAnswer(answer);
    }

    public int insertAnswerBySurveyAnswerDTO(SurveyAnswerDTO answer) {
        return surveyMapper.insertAnswerBySurveyAnswerDTO(answer);
    }
}
