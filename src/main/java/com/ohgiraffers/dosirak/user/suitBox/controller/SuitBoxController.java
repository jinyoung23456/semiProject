package com.ohgiraffers.dosirak.user.suitBox.controller;

import com.ohgiraffers.dosirak.admin.suitBox.model.dto.SuitBoxMenuDTO;
import com.ohgiraffers.dosirak.user.suitBox.model.dao.SuitBoxMapper;
import com.ohgiraffers.dosirak.user.suitBox.model.service.SuitBoxService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/suit-box")
public class SuitBoxController {
    private final SuitBoxService suitBoxService;

    public SuitBoxController(SuitBoxService suitBoxService) {
        this.suitBoxService = suitBoxService;
    }

    @GetMapping("")
    public String suitBox(Model model) {
        List<SuitBoxMenuDTO> saleMenuList = suitBoxService.selectSaleMenu();
        model.addAttribute("menuList", saleMenuList);
        return"user/suitBox/productView_suitBox";
    }
}
