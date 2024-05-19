package com.ohgiraffers.dosirak.user.myInfo.controller;

import com.ohgiraffers.dosirak.admin.login.model.AdminLoginDetails;
import com.ohgiraffers.dosirak.admin.login.model.service.AdminLoginService;
import com.ohgiraffers.dosirak.admin.member.model.dto.MemberDTO;
import com.ohgiraffers.dosirak.common.member.MemberModifyException;
import com.ohgiraffers.dosirak.user.login.model.dto.LoginDTO;
import com.ohgiraffers.dosirak.user.myInfo.model.service.MyinfoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Random;

@Controller
@RequestMapping("/user/myinfo")
public class MyinfoController {

    private final MyinfoService myinfoService;
    private final MessageSourceAccessor messageSourceAccessor;
    private final AdminLoginService adminLoginService;
    private final PasswordEncoder passwordEncoder;

    public MyinfoController(MyinfoService myinfoService, MessageSourceAccessor messageSourceAccessor, AdminLoginService adminLoginService, PasswordEncoder passwordEncoder){
        this.myinfoService = myinfoService;
        this.messageSourceAccessor = messageSourceAccessor;
        this.adminLoginService = adminLoginService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("pwdCheck")
    public void pwdCheck(){}

    @PostMapping("default")
    public ModelAndView pwdCheckForMyinfo(ModelAndView mv, RedirectAttributes rttr, @AuthenticationPrincipal AdminLoginDetails details, @RequestParam String pwd, Model model){
        // 페이지 랜덤코드 생성 : 이메일 인증번호 저장시 사용
        Random random = new Random();
        int createNum = 0;
        String randomNum = "";
        int letter = 12;
        String resultNum = "";
        for(int i=0; i<letter; i++){
            createNum = random.nextInt(9);
            randomNum = Integer.toString(createNum);
            resultNum += randomNum;
        }
        model.addAttribute("resultNum", resultNum);


        String loginPwd = details.getLoginDTO().getPwd();

        if(passwordEncoder.matches(pwd,loginPwd)){
            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.pwdCheckTrue"));

            String id = details.getLoginDTO().getId();
            MemberDTO member = myinfoService.myinfoSelect(id);
            model.addAttribute("member", member);

            mv.setViewName("/user/myinfo/default");
        }else{
            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.pwdCheckFalse"));
            mv.setViewName("/user/main");
        }

        return mv;
    }
    @GetMapping("default")
    public String myinfo(@AuthenticationPrincipal AdminLoginDetails details, Model model){
        // 페이지 랜덤코드 생성 : 이메일 인증번호 저장시 사용
        Random random = new Random();
        int createNum = 0;
        String randomNum = "";
        int letter = 12;
        String resultNum = "";
        for(int i=0; i<letter; i++){
            createNum = random.nextInt(9);
            randomNum = Integer.toString(createNum);
            resultNum += randomNum;
        }
        model.addAttribute("resultNum", resultNum);

        String id = details.getLoginDTO().getId();
        MemberDTO member = myinfoService.myinfoSelect(id);
        model.addAttribute("member", member);

        return "user/myinfo/default";
    }
    @PostMapping("modifyMyinfo")
    public String modifyMyinfo(MemberDTO member, RedirectAttributes rttr, @AuthenticationPrincipal AdminLoginDetails details) throws MemberModifyException {
        if(member.getAgree() == "") member.setAgree(null);
        if(member.getAddress1() == "") member.setAddress1(null);
        if(member.getAddress2() == "") member.setAddress2(null);
        if(member.getAddress3() == "") member.setAddress3(null);

        String id = details.getLoginDTO().getId();
        member.setId(id);
        myinfoService.modifyMyinfo(member);
        SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(details.getLoginDTO().getId()));
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.modifyMyinfo"));

        return "redirect:/user/myinfo/default";
    }

    protected Authentication createNewAuthentication(String memberId) {
        UserDetails newPrincipal = adminLoginService.loadUserByUsername(memberId);
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, newPrincipal.getPassword(), newPrincipal.getAuthorities());

        return newAuth;
    }

    @GetMapping("withdrawal")
    public void withdrawal(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();

            if(principal instanceof AdminLoginDetails){
                AdminLoginDetails adminLoginDetails = (AdminLoginDetails) principal;
                LoginDTO login = adminLoginDetails.getLoginDTO();
                String myId = login.getId();

                model.addAttribute("myId", myId);
            }
        }
    }

//    @PostMapping("withdrawalDone")
//    public void withdrawalMember(MemberDTO member, RedirectAttributes rttr, @AuthenticationPrincipal AdminLoginDetails details) throws MemberModifyException {
//        String id = details.getLoginDTO().getId();
//        member.setId(id);
//        member.setWithdrawal(true);
//        myinfoService.withdrawalMember(member);
//        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.withdrawal"));
//    }

    @PostMapping("withdrawalDone")
    public String withdrawalDone(MemberDTO member, RedirectAttributes rttr, @AuthenticationPrincipal AdminLoginDetails details, HttpServletRequest request, HttpServletResponse response) throws MemberModifyException {

        
        // 탈퇴여부 상태 변경
        String id = details.getLoginDTO().getId();
        member.setId(id);
        member.setWithdrawal(true);
        myinfoService.withdrawalMember(member);
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.withdrawal"));
        
        // 로그아웃 처리
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();        
        if(auth != null) new SecurityContextLogoutHandler().logout(request, response, auth);

        return "/user/myinfo/withdrawalDone";
    }
}
