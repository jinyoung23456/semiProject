package com.ohgiraffers.dosirak.user.order.controller;

import com.ohgiraffers.dosirak.admin.login.model.AdminLoginDetails;
import com.ohgiraffers.dosirak.user.login.model.dto.LoginDTO;
import com.ohgiraffers.dosirak.user.order.model.dao.OrderUserMapper;
import com.ohgiraffers.dosirak.user.order.model.dto.CartDTO;
import com.ohgiraffers.dosirak.user.order.model.dto.OrderHistoryDTO;
import com.ohgiraffers.dosirak.user.order.model.service.OrderUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OrderListController {
    private final OrderUserService orderUserService;
    public  OrderListController(OrderUserService orderUserService){
        this.orderUserService = orderUserService;
    }

        @GetMapping("/history/{userId}")
        public ResponseEntity<List<OrderHistoryDTO>> getOrderHistory(@PathVariable String userId, Model model) {
            List<OrderHistoryDTO> orderHistory =orderUserService.findOrderHistoryByUserId(userId);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String managerAuthor="";
            if(authentication != null && authentication.isAuthenticated()){
                Object principal = authentication.getPrincipal();
                if(principal instanceof AdminLoginDetails){
                    AdminLoginDetails adminLoginDetails = (AdminLoginDetails) principal;
                    LoginDTO login = adminLoginDetails.getLoginDTO();
                    managerAuthor = login.getAuthority();
                    userId = login.getId();
                }
                model.addAttribute("orderHistory", orderHistory);

            }

            return ResponseEntity.ok(orderHistory);

        }
}
