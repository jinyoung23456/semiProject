package com.ohgiraffers.dosirak.admin.member.contoller;

import com.ohgiraffers.dosirak.admin.login.model.AdminLoginDetails;
import com.ohgiraffers.dosirak.admin.member.model.dto.ManagerDTO;
import com.ohgiraffers.dosirak.admin.member.model.dto.MemberDTO;
import com.ohgiraffers.dosirak.admin.member.model.service.MemberService;
import com.ohgiraffers.dosirak.common.member.MemberModifyException;
import com.ohgiraffers.dosirak.common.member.MemberRegistException;
import com.ohgiraffers.dosirak.user.login.model.dto.LoginDTO;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequestMapping("/admin/member")
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final MessageSourceAccessor messageSourceAccessor;

    public MemberController(MemberService memberService, PasswordEncoder passwordEncoder, MessageSourceAccessor messageSourceAccessor){
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @GetMapping("/memberList")
    public String findMemberList(@RequestParam(required = false) String searchCondition,
                                 @RequestParam(required = false) String searchValue,
                                 Model model) {
        List<MemberDTO> memberList;

        if (searchValue != null && !searchValue.isEmpty()) {
            memberList = memberService.searchMemberForm(searchCondition, searchValue);
        } else {
            memberList = memberService.findAllMember();
        }

        model.addAttribute("memberList", memberList);
        model.addAttribute("searchCondition", searchCondition);
        model.addAttribute("searchValue", searchValue);

        return "/admin/member/memberList";
    }

    @GetMapping("/memberView")
    public String getMemberView(@RequestParam String id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();

            if(principal instanceof AdminLoginDetails){
                AdminLoginDetails adminLoginDetails = (AdminLoginDetails) principal;
                LoginDTO login = adminLoginDetails.getLoginDTO();
                String managerAuthor = login.getAuthority();

                model.addAttribute("managerAuthor", managerAuthor);
            }
        }

        MemberDTO member = memberService.selectMemberView(id);
        model.addAttribute("member", member);

        return "/admin/member/memberView";
    }

    @PostMapping("/modifyMember")
    public String modifyMember(MemberDTO member, RedirectAttributes rttr) throws MemberModifyException {
        if(member.getAgree() == "") member.setAgree(null);
        if(member.getAddress1() == "") member.setAddress1(null);
        if(member.getAddress2() == "") member.setAddress2(null);
        if(member.getAddress3() == "") member.setAddress3(null);

        memberService.modifyMember(member);
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.modify"));

        return "redirect:/admin/member/memberList";
    }
    @GetMapping("/memberPwdReset")
    public String memberPwdReset(@RequestParam String id, MemberDTO member, RedirectAttributes rttr) throws MemberModifyException {
        member.setPwd(passwordEncoder.encode(id));
        memberService.memberPwdReset(member);
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.pwdReset"));

        return "redirect:/admin/member/memberView?id="+member.getId();
    }

    @GetMapping("/managerList")
    public String findManagerList(@RequestParam(required = false) String searchCondition,
                                 @RequestParam(required = false) String searchValue,
                                 Model model) {
        List<ManagerDTO> managerList;

        if (searchValue != null && !searchValue.isEmpty()) {
            managerList = memberService.searchManagerForm(searchCondition, searchValue);
        } else {
            managerList = memberService.findAllManager();
        }

        model.addAttribute("managerList", managerList);
        model.addAttribute("searchCondition", searchCondition);
        model.addAttribute("searchValue", searchValue);

        return "/admin/member/managerList";
    }

    @GetMapping("/managerView")
    public String getManagerView(@RequestParam String id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();

            if(principal instanceof AdminLoginDetails){
                AdminLoginDetails adminLoginDetails = (AdminLoginDetails) principal;
                LoginDTO login = adminLoginDetails.getLoginDTO();
                String managerAuthor = login.getAuthority();
                String managerId = login.getId();

                model.addAttribute("managerAuthor", managerAuthor);
                model.addAttribute("managerId", managerId);
            }
        }

        ManagerDTO manager = memberService.selectManagerView(id);
        model.addAttribute("manager", manager);

        return "/admin/member/managerView";
    }
    @PostMapping("/modifyManager")
    public String modifyManager(ManagerDTO manager, RedirectAttributes rttr) throws MemberModifyException {
        if(manager.getContact() == "") manager.setContact(null);

        memberService.modifyManager(manager);
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("manager.modify"));

        return "redirect:/admin/member/managerList";
    }
    @GetMapping("/managerPwdReset")
    public String managerPwdReset(@RequestParam String id, ManagerDTO manager, RedirectAttributes rttr) throws MemberModifyException {
        manager.setPwd(passwordEncoder.encode(id));
        memberService.managerPwdReset(manager);
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("manager.pwdReset"));

        return "redirect:/admin/member/managerView?id="+manager.getId();
    }

    @GetMapping("join")
    public String join(Model model){
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

        return "/admin/member/join";
    }

    @PostMapping("idDupCheck")
    public ResponseEntity<String> checkDuplication(@RequestBody ManagerDTO manager){
        String result = "";
        if(manager.getEmail() == null || manager.getEmail() == ""){
            result = "이메일을 입력해주세요";
        }else{
            String[] emailSplit = manager.getEmail().split("@");
            manager.setId(emailSplit[0]);

            result = "사용 가능한 이메일입니다.";
            if(memberService.checkDuplication(manager.getId())) result = "중복 이메일이 존재합니다.";
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("regist")
    public String registManager(ManagerDTO manager, RedirectAttributes rttr) throws MemberRegistException {
        String[] emailSplit = manager.getEmail().split("@");
        manager.setId(emailSplit[0]);
        manager.setPwd(passwordEncoder.encode(manager.getPwd()));
        memberService.registManager(manager);
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("manager.regist"));

        return "redirect:/";
    }

}
