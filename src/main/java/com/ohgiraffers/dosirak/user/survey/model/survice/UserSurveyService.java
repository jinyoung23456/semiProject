package com.ohgiraffers.dosirak.user.survey.model.survice;

import com.ohgiraffers.dosirak.admin.survey.model.dto.SurveyQuestionDTO;
import com.ohgiraffers.dosirak.admin.survey.model.dto.SurveyResultDTO;
import com.ohgiraffers.dosirak.admin.survey.model.dto.SurveyScoreRangeDTO;
import com.ohgiraffers.dosirak.user.survey.model.dao.UserSurveyDAO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserSurveyService {
    final private UserSurveyDAO mapper;
    public UserSurveyService(UserSurveyDAO mapper) {
        this.mapper = mapper;
    }


    public List<SurveyQuestionDTO> getCurrentSurvey() {
        return mapper.getCurrentSurvey();
    }


    public void setScore(SurveyResultDTO result, Map<String, String> resultMap) {
        int carboScore = 0;
        int proteinScore = 0;
        int fatScore = 0;
        int exerciseScore = 0;
        int carboResultScore = 1;
        int proteinResultScore = 1;
        int fatResultScore = 1;
        int exerciseResultScore = 1;
        for(String resultMapKey : resultMap.keySet()){
            if(resultMapKey.contains("score")){
//                key = 카테고리(C,P,F,W) = value = 점수
                if(resultMapKey.contains("C")) {
                    carboScore = carboScore + Integer.parseInt(resultMap.get(resultMapKey));
                }
                if(resultMapKey.contains("P")) {
                    proteinScore = proteinScore + Integer.parseInt(resultMap.get(resultMapKey));
                }
                if(resultMapKey.contains("F")) {
                    fatScore = fatScore + Integer.parseInt(resultMap.get(resultMapKey));
                }
                if(resultMapKey.contains("E")) {
                    exerciseScore = exerciseScore + Integer.parseInt(resultMap.get(resultMapKey));
                }
            }
        }
        List<SurveyScoreRangeDTO> rangeList = mapper.getCurrentRange();
        for(SurveyScoreRangeDTO range : rangeList){
            switch (range.getCategory()){
                case "carbo": if(carboScore > range.getRange1()){
                    carboResultScore = 2;} if(carboScore > range.getRange2()){
                    carboResultScore = 3;} if (carboScore > range.getRange3()){
                    carboResultScore = 4;} if(carboScore > range.getRange4()){
                    carboResultScore = 5;} break;
                case "protein": if(proteinScore > range.getRange1()){
                    proteinResultScore = 2;} if(proteinScore > range.getRange2()){
                    proteinResultScore = 3;} if(proteinScore > range.getRange3()){
                    proteinResultScore = 4;} if(proteinScore > range.getRange4()){
                    proteinResultScore = 5;} break;
                case "fat": if(fatScore > range.getRange1()){
                    fatResultScore = 2;} if (fatScore > range.getRange2()){
                    fatResultScore = 3;} if (fatScore > range.getRange3()){
                    fatResultScore = 4;} if (fatScore > range.getRange4()){
                    fatResultScore = 5;} break;
                case "exercise": if(exerciseScore > range.getRange1()){
                    exerciseResultScore = 2;} if (exerciseScore > range.getRange2()){
                    exerciseResultScore = 3;} if (exerciseScore > range.getRange3()){
                    exerciseResultScore = 4;} if (exerciseScore > range.getRange4()){
                    exerciseResultScore = 5;} break;
            }
        }
        result.setSurveyCarboScore(carboResultScore);
        result.setSurveyProteinScore(proteinResultScore);
        result.setSurveyFatScore(fatResultScore);
        result.setSurveyExerciseScore(exerciseResultScore);
    }

    public int setResult(SurveyResultDTO result) {
        return mapper.setResult(result);
    }
}

