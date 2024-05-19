package com.ohgiraffers.dosirak.admin.survey.controller;

import com.ohgiraffers.dosirak.admin.survey.model.dto.*;
import com.ohgiraffers.dosirak.admin.survey.model.service.SurveyAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/survey")
public class SurveyAdminController {
    private final SurveyAdminService surveyAdminService;
    public SurveyAdminController(SurveyAdminService surveyAdminService) {
        this.surveyAdminService = surveyAdminService;
    }

    @GetMapping("result-list")
    public String resultList(Model model){
        model.addAttribute("surveyList", surveyAdminService.surveyList());
        return "admin/survey/surveyList";
    }
    @GetMapping("version-list")
    public String versionList(Model model){
        List<SurveyVersionDTO> versionList = surveyAdminService.getAllVersion();
        model.addAttribute("versionList", versionList);
        return "admin/survey/surveyVersionList";
    }
    @GetMapping("version-detail")
    public String versionList(Model model, @RequestParam int versionId){
        SurveyVersionDTO version = surveyAdminService.getVersionByVersionId(versionId);
        List<SurveyQuestionDTO> questionList = surveyAdminService.getQuestionListByVersionId(versionId);
        model.addAttribute("version", version);
        model.addAttribute("questionList", questionList);
        List<SurveyScoreRangeDTO> rangeLIst = surveyAdminService.getSurveyRangeByVersionId(versionId);
        model.addAttribute("rangeList", rangeLIst);
        return "admin/survey/surveyVersionView";
    }
    @GetMapping("version-insert")
    public String setNewVersion(Model model){
        int versionId = 0;
        int result = surveyAdminService.insertNetVersion();
        versionId = surveyAdminService.getVersionId();
        SurveyVersionDTO version = surveyAdminService.getVersionByVersionId(versionId);
        model.addAttribute("version", version);
        return "admin/survey/surveyVersionView";
    }
    @GetMapping("version-delete")
    public String versionDelete(Model model, @RequestParam int versionId){
        int result = surveyAdminService.deleteVersionByVersionId(versionId);
        model.addAttribute("deleteResult", result);
        return "redirect:/admin/survey/version-list";
    }

    @GetMapping("version-set")
    public String setNewSurvey(Model model, @RequestParam int versionId){
        SurveyVersionDTO version = surveyAdminService.getVersionByVersionId(versionId);
        List<SurveyQuestionDTO> questionList = surveyAdminService.getQuestionListByVersionId(versionId);
        List<SurveyScoreRangeDTO> rangeLIst = surveyAdminService.getSurveyRangeByVersionId(versionId);
        model.addAttribute("version", version);
        model.addAttribute("questionList", questionList);
        model.addAttribute("rangeList", rangeLIst);
        return "admin/survey/surveyVersionSet";
    }

    @PostMapping("fetch/version")
    public @ResponseBody int versionSet(@ModelAttribute SurveyVersionDTO version){
        int result = 0;
        result = surveyAdminService.updateVersionByVersionDTO(version);
        return 1;
    }
    @PostMapping("fetch/question")
    public @ResponseBody int questionSet(@ModelAttribute SurveyQuestionDTO question){
        System.out.println(question);
        int result = 0;
        int result2= surveyAdminService.updateQuestionBySurveyQuestionDTO(question);
        System.out.println(result2);
        result = surveyAdminService.getQuestionIdByQuestionDTO(question);
        return result;
    }
    @PostMapping("fetch/answer")
    public @ResponseBody int answerSet(@ModelAttribute SurveyAnswerDTO answer){
        surveyAdminService.deleteAllAnswerByAnswer(answer);
        int result = 0;
        result = surveyAdminService.insertAnswerBySurveyAnswerDTO(answer);
        return result;
    }


}
