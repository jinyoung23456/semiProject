package com.ohgiraffers.dosirak.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping("/login")
    public String login(){return "login";}

    @GetMapping("/loginFail")
    public ModelAndView loginFail(ModelAndView mv, @RequestParam String message){
        mv.addObject("message", message);
        mv.setViewName("/loginFail");
        return mv;
    }

    @GetMapping("/logoutPage")
    public void logoutPage(){}
}
