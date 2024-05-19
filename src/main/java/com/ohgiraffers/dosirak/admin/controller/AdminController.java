package com.ohgiraffers.dosirak.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

//    @GetMapping("/main")
//    public void main(){}

    @GetMapping("main")
    public ModelAndView main(ModelAndView mv, @RequestParam(required = false) String message){
        mv.addObject("message", message);
        mv.setViewName("/admin/main");
        return mv;
    }
}
