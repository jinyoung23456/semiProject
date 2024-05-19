package com.ohgiraffers.dosirak.admin.review;


import com.ohgiraffers.dosirak.admin.login.model.AdminLoginDetails;
import com.ohgiraffers.dosirak.admin.review.model.dto.AdminDTO;
import com.ohgiraffers.dosirak.admin.review.model.dto.AnswerDTO;
import com.ohgiraffers.dosirak.admin.review.model.dto.ReviewDTO;
import com.ohgiraffers.dosirak.admin.review.model.service.ReviewService;
import com.ohgiraffers.dosirak.user.login.model.dto.LoginDTO;
import com.ohgiraffers.dosirak.user.review.UserReviewController;
import com.ohgiraffers.dosirak.user.review.model.dto.ReviewImgDTO;
import com.ohgiraffers.dosirak.user.review.model.service.UserReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final MessageSourceAccessor messageSourceAccessor;

    private final UserReviewService userReviewService;

    public ReviewController(ReviewService reviewService, MessageSourceAccessor messageSourceAccessor, UserReviewService userReviewService) {
        this.reviewService = reviewService;
        this.messageSourceAccessor = messageSourceAccessor;
        this.userReviewService = userReviewService;
    }

    @GetMapping("reviewList")
    public String allReviewList(Model model){


        List<ReviewDTO> reviewDTOList = reviewService.allReview();
        for(ReviewDTO r : reviewDTOList){
            System.out.println(r);
        }


        model.addAttribute("reviewList", reviewDTOList);

        return "admin/review/reviewList";
    }

    @GetMapping("/answerDetail")
    public String getAnswerDetail(@RequestParam("answerCode") int answerCode, Model model) {

        AnswerDTO answerDetail = reviewService.selectAnswerDetail(answerCode);
        model.addAttribute("answer", answerDetail);

        return "admin/review/reviewDetail";
    }

    @GetMapping("/reviewDetail")
    public String getNoticeDetail(@RequestParam("reviewCode") int reviewCode, Model model) {

        ReviewDTO reviewDTO = reviewService.getReviewInfo(reviewCode);
        model.addAttribute("reviewDTO", reviewDTO);

        List<ReviewImgDTO> imageList = userReviewService.getImageList(reviewDTO.getReviewCode());
        model.addAttribute("imageList", imageList);

        return "admin/review/reviewDetail";
    }

    @PostMapping("/reviewAnswerRegist")
    public String reviewAnswerRegist(ReviewDTO reviewDTO, @RequestParam String answerContent, RedirectAttributes rttr){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();

            if(principal instanceof AdminLoginDetails){
                AdminLoginDetails adminLoginDetails = (AdminLoginDetails) principal;
                LoginDTO login = adminLoginDetails.getLoginDTO();
                String adminId = login.getId();

                reviewDTO.setAdminId(adminId);
            }
        }

        reviewDTO.setAnswerContent(answerContent);

        int result = reviewService.reviewAnswerRegist(reviewDTO);

        if(result > 0) {
            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("review.answerSuccess"));
        }else{
            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("review.answerFail"));
        }

        return "redirect:/admin/review/reviewDetail?reviewCode="+reviewDTO.getReviewCode();
    }

    @GetMapping("/reviewDelete")
    public String reviewDelete(@RequestParam int reviewCode, RedirectAttributes rttr){
        int result = reviewService.reviewDelete(reviewCode);

        if(result > 0) {
            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("review.admin.deleteSuccess"));
        }else{
            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("review.admin.deleteFail"));
        }

        return "/admin/review/reviewList";
    }


    @PostMapping("registAnswer")
    public ResponseEntity<String> registAnswer(@RequestBody AnswerDTO registAnswer,
                                               @AuthenticationPrincipal AdminDTO adminId) {
        registAnswer.setAdminId(adminId);

        reviewService.registAnswer(registAnswer);

        return ResponseEntity.ok("댓글 등록 완료");
    }

//    @GetMapping("/reviewDetail")
//    public String getAskDetail(@RequestParam("reviewCode") int reviewCode, Model model) {
//
//        ReviewDTO reviewDetail = reviewService.selectReviewDetail(reviewCode);
////        AnswerDTO answerDetail = reviewService.selectAnswerDetail(reviewCode);
////        List<AskCategoryDTO> categoryList = customerService.findCategoryList();
//
//        model.addAttribute("review", reviewDetail);
////        model.addAttribute("answer", answerDetail);
////        model.addAttribute("askCategory", categoryList);
//
//        return "admin/review/reviewDetail";
//    }
}
