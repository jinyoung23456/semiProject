package com.ohgiraffers.dosirak.user.customer.controller;

import com.ohgiraffers.dosirak.admin.customer.model.dto.AskCategoryDTO;
import com.ohgiraffers.dosirak.admin.customer.model.dto.ImgDTO;
import com.ohgiraffers.dosirak.admin.customer.model.dto.QnaDTO;
import com.ohgiraffers.dosirak.admin.customer.model.service.CustomerService;
import com.ohgiraffers.dosirak.admin.login.model.AdminLoginDetails;
import com.ohgiraffers.dosirak.admin.member.model.dto.MemberDTO;
import com.ohgiraffers.dosirak.user.customer.model.dto.*;
import com.ohgiraffers.dosirak.user.customer.model.service.UserCustomerService;
import com.ohgiraffers.dosirak.user.login.model.dto.LoginDTO;
import com.ohgiraffers.dosirak.user.myInfo.model.service.MyinfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/user/customer")
public class UserCustomerController {

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    private final UserCustomerService userCustomerService;
    private final MyinfoService myinfoService;

    public UserCustomerController(UserCustomerService userCustomerService, CustomerService customerService, MyinfoService myinfoService) {
        this.userCustomerService = userCustomerService;
        this.myinfoService = myinfoService;
    }


    /* ----- 고객센터 메인 ----- */

    @GetMapping("/main")
    public String getMainePage(Model model) {


        return "user/customer/main";
    }

    /* ----- 공지사항 ----- */

    @GetMapping("/noticeList")
    public String getNoticePage(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(required = false) String searchCondition,
                                @RequestParam(required = false) String searchValue,
                                Model model) {

        log.info("noticeList page : {}", page);
        log.info("noticeList searchCondition : {}", searchCondition);
        log.info("noticeList searchValue : {}", searchValue);

        // 페이징 시작

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        Map<String, Object> askListAndPaging = userCustomerService.selectNoticeList(searchMap, page);
        model.addAttribute("paging", askListAndPaging.get("paging"));
        model.addAttribute("noticeList", askListAndPaging.get("noticeList"));

        UserAskDTO userAskDTO = new UserAskDTO();
        model.addAttribute("userAskDTO", userAskDTO);

        return "user/customer/noticeList";
    }

    @GetMapping("/noticeDetail")
    public String getNoticeDetail(@RequestParam("noticeCode") int noticeCode, Model model) {

        UserNoticeDTO noticeDetail = userCustomerService.selectNoticeDetail(noticeCode);

        model.addAttribute("notice", noticeDetail);

        return "user/customer/noticeDetail";
    }


    /* ----- 자주 묻는 질문 ----- */

    @GetMapping("/qnaList")
    public String getQnaPage(Model model) {

        List<UserQnaDTO> qnaList = userCustomerService.findQnaList();

        model.addAttribute("qnaList", qnaList);

        return "user/customer/qnaList";
    }

    @GetMapping("/qnaDetail")
    public String getQnaDetail(@RequestParam("qnaCode") int qnaCode, Model model) {

        UserQnaDTO qnaDetail = userCustomerService.selectQnaDetail(qnaCode);
        List<UserAskCategoryDTO> categoryList = userCustomerService.findCategoryList();

        model.addAttribute("qna", qnaDetail);
        model.addAttribute("category", categoryList);

        return "user/customer/qnaDetail";
    }


    /* ----- 1대1 문의 ----- */

    @GetMapping("/askList")
    public String getAskPage(@RequestParam(defaultValue = "1") int page,
                             @RequestParam(required = false) String searchCondition,
                             @RequestParam(required = false) String searchValue,
                             Model model) {

        log.info("boardList page : {}", page);
        log.info("boardList searchCondition : {}", searchCondition);
        log.info("boardList searchValue : {}", searchValue);

        // 페이징 시작

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        Map<String, Object> askListAndPaging = userCustomerService.selectAskList(searchMap, page);
        model.addAttribute("paging", askListAndPaging.get("paging"));
        model.addAttribute("askList", askListAndPaging.get("askList"));

        UserAskDTO userAskDTO = new UserAskDTO();
        model.addAttribute("userAskDTO", userAskDTO);

        return "user/customer/askList";
    }

    @GetMapping("/askDetail")
    public String getAskDetail(@RequestParam("askCode") int askCode, Model model) {

        UserAskDTO askList = userCustomerService.findAskList(askCode);
        model.addAttribute("askList", askList);

        List<UserCustomerImgDTO> imageList = userCustomerService.searchImageList(askCode);
        model.addAttribute("imageList", imageList);
        log.info("imageList : {}", imageList);

        return "user/customer/askDetail";
    }

    @GetMapping("/askEdit/{askCode}")
    public String getAskEdit(@PathVariable("askCode") int askCode, Model model) {

        UserAskDTO askList = userCustomerService.findAskList(askCode);
        model.addAttribute("askList", askList);
        log.info("askList : {}", askList);

        List<UserAskCategoryDTO> categoryList = userCustomerService.findCategoryList();
        model.addAttribute("categoryList", categoryList);

        List<UserCustomerImgDTO> imageList = userCustomerService.searchImageList(askCode);
        model.addAttribute("imageList", imageList);

        return "user/customer/askEdit";
    }

    @PostMapping("/askUpdate/{askCode}")
    public String askUpdate(@PathVariable("askCode") int askCode, UserAskDTO ask) {

        //askTemp의 현재 ask 정보 담아 반환
        UserAskDTO askTemp = userCustomerService.findAskList(askCode);
        log.info("askTemp : {}", askTemp);

        //덮어쓰기
        askTemp.setAskCategoryCode(ask.getAskCategoryCode());
        askTemp.setAskTitle(ask.getAskTitle());
        askTemp.setAskContent(ask.getAskContent());

        //업데이트
        userCustomerService.updateAsk(askTemp);

        return "redirect:/user/customer/askList";
    }

    @RequestMapping("/askDelete")
    public String askDelete(UserAskDTO ask) {

        userCustomerService.deleteQna(ask.getAskCode());

        return "redirect:/user/customer/askList";
    }

    @GetMapping("/askRegist")
    public String getRegistPage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof AdminLoginDetails adminLoginDetails) {
                LoginDTO login = adminLoginDetails.getLoginDTO();
                String id = login.getId();

                MemberDTO member = myinfoService.myinfoSelect(id);
                String memberEmail = member.getEmail();
                model.addAttribute("memberEmail", memberEmail);

                /* 카테고리 리스트 조회 및 주입 */
                List<UserAskCategoryDTO> categoryList = userCustomerService.findCategoryList();
                model.addAttribute("askCategory", categoryList);
                log.info("askCategoryList : {}", categoryList);

                model.addAttribute("login", login);
            }
        }
        return "user/customer/askRegist";
    }

    @PostMapping("/askRegist")
    public String testFileUpload(@RequestParam List<MultipartFile> attachImage,
                                 String askTitle, String askContent, int askCategoryCode,
                                 UserAskDTO ask, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();

            if(principal instanceof AdminLoginDetails adminLoginDetails){
                LoginDTO login = adminLoginDetails.getLoginDTO();
                String userId = login.getId();

                /* 질문 등록하기 */
                UserAskDTO newAsk = new UserAskDTO();
                newAsk.setAskTitle(askTitle);
                newAsk.setAskContent(askContent);
                newAsk.setUserId(userId);
                newAsk.setAskCategoryCode(askCategoryCode);

                userCustomerService.askRegist(newAsk);

                /* 가장 최신 질문 조회 */
                UserAskDTO lastAsk = userCustomerService.findLastAsk();
                log.info("lastAsk : {}", lastAsk);

                /* 경로 설정 */
                String fileUploadDir = IMAGE_DIR;

                File dir1 = new File(fileUploadDir);

                /* 디렉토리가 없을 경우 생성한다. */
                if (!dir1.exists()) {
                    dir1.mkdirs();
                }

                /* 업로드 파일에 대한 정보를 담을 리스트 */
                List<UserCustomerImgDTO> imageList = new ArrayList<>();

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

                            /* DB에 저장할 파일의 정보 처리 */
                            UserCustomerImgDTO fileInfo = new UserCustomerImgDTO();
                            fileInfo.setOriginalName(originalFileName);
                            fileInfo.setSavedName(saveFileName);
                            fileInfo.setSavePath(fileUploadDir);

                            /* 이미지 DTO에 요청 코드 설정 */
                            fileInfo.setRefAskCode(lastAsk.getAskCode());

                            /* 리스트에 파일 정보 저장 */
                            imageList.add(fileInfo);
                            log.info("imageList : {}", imageList);
                        }
                    }
                    /* 이미지 리스트를 한 번에 DB에 저장 */
                    userCustomerService.registImageList(imageList);

                    model.addAttribute("message", "문의글이 등록되었습니다");

                } catch (IOException e) {
                    /* 파일 저장 중간에 실패 시, 이전에 저장된 파일 삭제 */
                    for (UserCustomerImgDTO image : imageList) {
                        new File(fileUploadDir + "/" + image.getSavedName()).delete();
                    }
                    model.addAttribute("message", "문의 등록에 실패하였습니다.");
                }
                log.info("imageList : {}", imageList);
            }
        }
        return "user/customer/result";
    }
}
