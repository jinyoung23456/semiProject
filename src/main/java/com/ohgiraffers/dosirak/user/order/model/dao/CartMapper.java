package com.ohgiraffers.dosirak.user.order.model.dao;

import com.ohgiraffers.dosirak.admin.member.model.dto.MemberDTO;
import com.ohgiraffers.dosirak.admin.order.model.dto.OrderDTO;
import com.ohgiraffers.dosirak.admin.order.model.dto.PayDTO;
import com.ohgiraffers.dosirak.admin.product.dto.productDTO;
import com.ohgiraffers.dosirak.user.order.model.dto.CartDTO;
import com.ohgiraffers.dosirak.user.product.dto.ProductUserDTO;
import com.ohgiraffers.dosirak.user.suitBox.model.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CartMapper {
    List<CartDTO> userCartList(String userId);



    void cartUpdateQuantity(int productCode, int updatedQuantity);

    List<MemberDTO> findSearchId();

    String findOrderCode();

    PayDTO findPayDate(String orderCode);

    MenuDTO getMenuByCode(int menuCode);

    void cartUpdateQuantitySuitbox(Map<String, String> suitBox);

    MemberDTO getPaymentByUserId(String memberId);

    CartDTO getProductByCart(Map<String, String> productAndId);

    CartDTO getSuitboxCodeByCart(Map<String, String> suitboxCodeAndId);

    void insertDetailSuitbox(CartDTO cart);

    void insertDetailProduct(CartDTO cart);

    int insertPay(Map<String, String> pay);

    int insertDelivery(String orderCode);


    void deleteCartSuitbox(String itemCode, String memberId);

    void deleteCartProduct(String itemCode, String memberId);

    void cartDeleteProductList(CartDTO productCode);

    void cartDeleteSuitboxList(CartDTO suitboxCode);

    int userOrderDone(String memberId, String name, String phone, String address1, String address2, String address3);
}
