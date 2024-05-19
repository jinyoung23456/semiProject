package com.ohgiraffers.dosirak.user.order.model.dao;

import com.ohgiraffers.dosirak.user.order.model.dto.CartDTO;
import com.ohgiraffers.dosirak.user.order.model.dto.OrderDTO;
import com.ohgiraffers.dosirak.user.order.model.dto.OrderHistoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderUserMapper {

    List<OrderHistoryDTO> findOrderHistoryByUserId(String userId);

}
