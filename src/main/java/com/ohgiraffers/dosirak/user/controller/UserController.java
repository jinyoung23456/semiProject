package com.ohgiraffers.dosirak.user.controller;

import com.ohgiraffers.dosirak.admin.member.model.dto.MemberDTO;
import com.ohgiraffers.dosirak.user.myInfo.model.service.MyinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private MyinfoService myinfoService;

    @GetMapping(value = {"/", "/user/main"})
    public ModelAndView main(ModelAndView mv, @RequestParam(required = false) String message){
        mv.addObject("message", message);
        mv.setViewName("/user/main");
        return mv;
    }

    @PostMapping("/user/emailDupCheck")
    public ResponseEntity<String> emailDupCheck(@RequestBody MemberDTO member){
        String result = "";
        if(member.getEmail() == null || member.getEmail() == ""){
            result = "이메일을 입력해주세요";
        }else{
            result = "사용가능한 이메일입니다.";
            if(myinfoService.emailDupCheck(member.getEmail())) result = "중복 이메일이 존재합니다.";
        }

        return ResponseEntity.ok(result);
    }
}
