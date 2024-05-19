package com.ohgiraffers.dosirak.admin.suitBox.controller;

import com.ohgiraffers.dosirak.admin.suitBox.model.dto.SuitBoxMenuDTO;
import com.ohgiraffers.dosirak.admin.suitBox.model.service.AdminSuitBoxService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/suit-box/menu")
public class AdminSuitBoxController {
    private final AdminSuitBoxService service;

    public AdminSuitBoxController(AdminSuitBoxService service) {
        this.service = service;
    }

    @GetMapping("/view")
    public String view(String searchValue, String category, Model model) {

        List<SuitBoxMenuDTO> menuList = service.getSuitBoxMenu();
        model.addAttribute("menuList", menuList);
        return "admin/suitBox/suitBoxMenuView";
    }

    @GetMapping("/set")
    public String set(String searchValue, String category, Model model) {
        model.addAttribute("setCondition", "regist");
        return "admin/suitBox/suitBoxMenuSet";
    }

    @PostMapping("/set-menu")
    public String setMenu(SuitBoxMenuDTO menu, Model model) {
        String result = "";
        int resultInt = service.registMenu(menu);
        if (resultInt == 1) {
            result = "redirect:/admin/suit-box/menu/view";
        }
        return result;
    }

    @GetMapping("/modify")
    public String modify(Model model, @RequestParam int menuCode) {
        SuitBoxMenuDTO targetMenu = service.menuFindByMenuCode(menuCode);
        model.addAttribute("setCondition", "modify");
        model.addAttribute("menu", targetMenu);
        return "admin/suitBox/suitBoxMenuSet";
    }

    @PostMapping("/modify-menu")
    public String modifyMenu(Model model, SuitBoxMenuDTO menu, @RequestParam int menuCode) {
        int modResult = service.modifyMenu(menu, menuCode);
        return "redirect:/admin/suit-box/menu/view";
    }

    @PostMapping("/delete-menu")
    public String deleteMenu(Model model, @RequestParam int menuCode) {
        int delResult = service.deleteMenu(menuCode);
        String result = "";
        if (delResult == 1) {
            result = "redirect:/admin/suit-box/menu/view";
        } else {
            result = "redirect:/admin/suit-box/menu/modify";
        }
        return result;
    }
    @PostMapping("/fetch-view")
    public @ResponseBody List<SuitBoxMenuDTO> viewFetch(@RequestBody Map<String,String> condition) {
        List<SuitBoxMenuDTO> menuList;
        if(condition.get("value").equals("all")||condition.get("criteria").equals("all")){
            menuList = service.getSuitBoxMenu();
        } else {
            menuList = service.searchMenuByCondition(condition);
        }
        return menuList;
    }
}