package com.ohgiraffers.dosirak.user.join.controller;

import com.ohgiraffers.dosirak.admin.member.model.dto.MemberDTO;
import com.ohgiraffers.dosirak.common.member.MemberRegistException;
import com.ohgiraffers.dosirak.user.join.model.service.JoinService;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Random;

@Controller
@RequestMapping("/user/join")
public class JoinCotroller {

    private final JoinService joinService;
    private final PasswordEncoder passwordEncoder;
    private final MessageSourceAccessor messageSourceAccessor;

    public JoinCotroller(JoinService joinService, PasswordEncoder passwordEncoder, MessageSourceAccessor messageSourceAccessor){
        this.joinService = joinService;
        this.passwordEncoder = passwordEncoder;
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @GetMapping("/agreeUse")
    public void agreeUse(){}

    @GetMapping("/agreeInfo")
    public void agreeInfo(){}

    @GetMapping("/agreeMarketing")
    public void agreeMarketing(){}

    @GetMapping("/join01")
    public void join01(){}

    @PostMapping("/join02")
    public void join02(@RequestParam(required = false) String mkAgree, Model model){
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

        // join01 페이지에서 마케팅동의여부 체크받아서 넘겨줌
        model.addAttribute("mkAgree", mkAgree);
    }


    @PostMapping("/idCheck")
    @ResponseBody
    public boolean idCheck(@RequestParam("id") String id){
        boolean result = joinService.idCheck(id);
        return result;
    }

    @PostMapping("/joinDone")
    public String joinDone(MemberDTO member) throws MemberRegistException {
        member.setPwd(passwordEncoder.encode(member.getPwd()));
        joinService.registMember(member);

        return "/user/join/joinDone";
    }

}
