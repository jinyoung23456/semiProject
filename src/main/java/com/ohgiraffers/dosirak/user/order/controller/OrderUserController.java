package com.ohgiraffers.dosirak.user.order.controller;

import com.ohgiraffers.dosirak.user.order.model.dto.OrderDTO;
import com.ohgiraffers.dosirak.user.order.model.service.OrderUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/*")
public class OrderUserController {
    private final OrderUserService orderService;
    @Autowired
    public OrderUserController(OrderUserService orderService){
        this.orderService=orderService;
    }

}
