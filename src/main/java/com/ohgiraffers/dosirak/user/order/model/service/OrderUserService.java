package com.ohgiraffers.dosirak.user.order.model.service;

import com.ohgiraffers.dosirak.user.order.model.dao.OrderUserMapper;
import com.ohgiraffers.dosirak.user.order.model.dto.CartDTO;
import com.ohgiraffers.dosirak.user.order.model.dto.OrderDTO;
import com.ohgiraffers.dosirak.user.order.model.dto.OrderHistoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderUserService {

    private OrderUserMapper orderUserMapper;

    public List<OrderHistoryDTO> findOrderHistoryByUserId(String userId) {
         return orderUserMapper.findOrderHistoryByUserId(userId);
    }

}
