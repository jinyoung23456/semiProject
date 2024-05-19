package com.ohgiraffers.dosirak.user.login.controller;

import com.ohgiraffers.dosirak.admin.member.model.dto.MemberDTO;
import com.ohgiraffers.dosirak.user.login.model.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
@RequestMapping("/user/login")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/findID")
    public String findID(Model model){
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

        return "/user/login/findID";
    }

    @PostMapping("/findIDdone")
    public String findIDdone(MemberDTO member, Model model){
        MemberDTO findIdMem = userLoginService.findIDform(member);
        model.addAttribute("findIdMem", findIdMem);

        if(findIdMem == null){
            model.addAttribute("message", "해당 정보로 가입된 계정이 존재하지 않습니다.");
            return "/user/login/findIDfail";
        }

        return "/user/login/findIDdone";
    }

    @GetMapping("/findPWD")
    public String findPWD(Model model){
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

        return "/user/login/findPWD";
    }

    @PostMapping("/findPWDreset")
    public String findPWDreset(MemberDTO member, Model model){
        MemberDTO findPWDMem = userLoginService.findPWDform(member);
        model.addAttribute("findPWDMem", findPWDMem);

        if(findPWDMem == null){
            model.addAttribute("message", "해당 정보로 가입된 계정이 존재하지 않습니다.");
            return "/user/login/findPWDfail";
        }

        return "/user/login/findPWDreset";
    }

    @PostMapping("/findPWDdone")
    public String findPWDdone(@RequestParam String pwd, @RequestParam String id, @RequestParam String role, Model model){
        String encodePwd = passwordEncoder.encode(pwd);
        boolean result;

        if(role.equals("ADMIN")){
            result = userLoginService.pwdResetAdmin(encodePwd, id);
        }else{
            result = userLoginService.pwdResetUser(encodePwd, id);
        }

        if(!result){
            model.addAttribute("message", "시스템 오류로 인해 비밀번호가 재설정되지 않았습니다. \n다시 시도해 주세요.");
            return "/user/login/findPWDfail";
        }

        return "/user/login/findPWDdone";
    }
}
