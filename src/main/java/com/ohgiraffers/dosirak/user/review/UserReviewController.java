package com.ohgiraffers.dosirak.user.review;

import com.ohgiraffers.dosirak.admin.login.model.AdminLoginDetails;
import com.ohgiraffers.dosirak.admin.member.contoller.MemberController;
import com.ohgiraffers.dosirak.user.customer.model.dto.UserAskDTO;
import com.ohgiraffers.dosirak.user.customer.model.dto.UserCustomerImgDTO;
import com.ohgiraffers.dosirak.user.login.model.dto.LoginDTO;
import com.ohgiraffers.dosirak.user.review.model.dto.ReviewDTO;
import com.ohgiraffers.dosirak.user.review.model.dto.ReviewImgDTO;
import com.ohgiraffers.dosirak.user.review.model.dto.UserReviewDTO;
import com.ohgiraffers.dosirak.user.review.model.service.UserReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/user/review")
public class UserReviewController {

    @Value("${image.reivew-image-dir}")
    private String IMAGE_DIR;

    private final UserReviewService userReviewService;
    private final MessageSourceAccessor messageSourceAccessor;

    public UserReviewController(UserReviewService userReviewService, MessageSourceAccessor messageSourceAccessor) {
        this.userReviewService = userReviewService;
        this.messageSourceAccessor = messageSourceAccessor;
    }


    @GetMapping("/list")
    public String allUserReviewList(@RequestParam(defaultValue = "1") int page,
                                    @RequestParam(required = false) String searchCondition,
                                    @RequestParam(required = false) String searchValue,
                                    Model model) {
        /* 리뷰 리스트 조회 */

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userId = "";
        if(authentication != null && authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();

            if(principal instanceof AdminLoginDetails){
                AdminLoginDetails adminLoginDetails = (AdminLoginDetails) principal;
                LoginDTO login = adminLoginDetails.getLoginDTO();
                userId = login.getId();


                List<ReviewDTO> reviewList = userReviewService.getReviewListById(userId);
                model.addAttribute("orderList", reviewList);

                List<ReviewDTO> myReviewList = userReviewService.getMyList(userId);
                model.addAttribute("myReviewList", myReviewList);

                // 페이징 시작

                log.info("boardList page : {}", page);
                log.info("boardList searchCondition : {}", searchCondition);
                log.info("boardList searchValue : {}", searchValue);

                Map<String, String> searchMap = new HashMap<>();
                searchMap.put("searchCondition", searchCondition);
                searchMap.put("searchValue", searchValue);

                Map<String, Object> reviewListAndPaging = userReviewService.selectReviewList(searchMap, page, userId);
                model.addAttribute("paging", reviewListAndPaging.get("paging"));
                model.addAttribute("reviewList", reviewListAndPaging.get("askList"));

                ReviewDTO reveiwDTO = new ReviewDTO();
                model.addAttribute("reveiwDTO", reveiwDTO);

                System.out.println(myReviewList);
            }
        }
        return "user/review/list";
    }

    @GetMapping("/userReview")
    public String getUserReviewDetail(ReviewDTO reviewDTO, Model model) {

        ReviewDTO myReview = userReviewService.getMyReview(reviewDTO);
        model.addAttribute("myReview",myReview);

        List<ReviewImgDTO> imageList = userReviewService.getImageList(reviewDTO.getReviewCode());
        model.addAttribute("imageList", imageList);

        return "user/review/userReview";
    }

    @PostMapping("/userReviewEdit")
    public String EditReview(ReviewDTO reviewDTO, RedirectAttributes rttr) {
        int result = userReviewService.EditReview(reviewDTO);
        if(result > 0) {
            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("review.modifySuccess"));
        }else{
            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("review.modifyFail"));
        }

        return "redirect:/user/review/userReview?reviewCode="+reviewDTO.getReviewCode();
    }

    @GetMapping("/userReviewDelete")
    public String deleteReview(@RequestParam int reviewCode, RedirectAttributes rttr) {
        int result = userReviewService.deleteReview(reviewCode);
        if(result > 0) {
            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("review.deleteSuccess"));
        }else{
            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("review.deleteFail"));
        }

        return "redirect:/user/review/list";
    }

    @PostMapping("/userReviewWrite")
    public String getRegistPage(@RequestParam String productName, @RequestParam String detailCode, Model model) {

        System.out.println(productName);

        model.addAttribute("productName", productName);
        model.addAttribute("detailCode", detailCode);

        return "user/review/userReviewWrite";
    }

    @PostMapping("/userReviewWriteDone")
    public String getReviewInformation(@RequestParam List<MultipartFile> attachImage,
                                       ReviewDTO reviewDTO, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();

            if(principal instanceof AdminLoginDetails adminLoginDetails){
                LoginDTO login = adminLoginDetails.getLoginDTO();
                String userId = login.getId();

                /* 리뷰 등록하기 */
                int result = userReviewService.getReviewDTOInformation(reviewDTO,userId);

                /* 경로 설정 */
                String fileUploadDir = IMAGE_DIR + "original";

                File dir1 = new File(fileUploadDir);

                /* 디렉토리가 없을 경우 생성한다. */
                if (!dir1.exists()) {
                    dir1.mkdirs();
                }

                /* 업로드 파일에 대한 정보를 담을 리스트 */
                List<ReviewImgDTO> imageList = new ArrayList<>();

                try {
                    for (int i = 0; i < attachImage.size(); i++) {
                        /* 첨부파일이 실제로 존재하는 경우 로직 수행 */
                        if (attachImage.get(i).getSize() > 0) {

                            String originalFileName = attachImage.get(i).getOriginalFilename();
                            log.info("originalFileName : {}", originalFileName);

                            String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
                            String saveFileName = UUID.randomUUID() + ext;
                            log.info("savedFileName : {}", saveFileName);

                            /* 서버의 설정 디렉토리에 파일 저장하기 */
                            attachImage.get(i).transferTo(new File(fileUploadDir + "/" + saveFileName));

                            /* 가장 최신 질문 조회 */
                            ReviewDTO lastReview = userReviewService.findLastReview();
                            log.info("lastReview : {}", lastReview);

                            /* DB에 저장할 파일의 정보 처리 */
                            ReviewImgDTO fileInfo = new ReviewImgDTO();
                            fileInfo.setOriginalName(originalFileName);
                            fileInfo.setSavedName(saveFileName);
                            fileInfo.setSavePath("/static/reviewUpload/original");

                            /* 이미지 DTO에 요청 코드 설정 */
                            fileInfo.setRefReviewCode(lastReview.getReviewCode());

                            /* 리스트에 파일 정보 저장 */
                            imageList.add(fileInfo);
                            log.info("imageList : {}", imageList);
                        }
                    }
                    /* 이미지 리스트를 한 번에 DB에 저장 */
                    userReviewService.registImageList(imageList);

                    model.addAttribute("message", "파일 업로드에 성공하였습니다.");

                } catch (IOException e) {
                    /* 파일 저장 중간에 실패 시, 이전에 저장된 파일 삭제 */
                    for (ReviewImgDTO image : imageList) {
                        new File(fileUploadDir + "/" + image.getSavedName()).delete();
                    }
                    model.addAttribute("message", "파일 업로드에 실패하였습니다.");
                }
                log.info("imageList : {}", imageList);
            }
        }

        return "user/review/userReviewWriteDone";
    }



}
