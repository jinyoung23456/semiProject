package com.ohgiraffers.dosirak.admin.order.model.dao;

import com.ohgiraffers.dosirak.admin.order.model.dto.*;
import com.ohgiraffers.dosirak.user.order.model.dto.CartDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<OrderDTO> allOrderLists();

    List<RefundDTO> allRefundList();

    List<DeliveryDTO> allDeliveryList();

    OrderDTO allOrderView(String orderCode);

    DetailDTO searchDetail(String orderCode);

    RefundDTO allRefundView(String orderCode);

    DeliveryDTO allDeliveryView(String orderCode);

    void updateOrderStatus(List<String> detailCode);

}
