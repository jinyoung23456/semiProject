package com.ohgiraffers.dosirak.user.order.model.service;

import com.ohgiraffers.dosirak.admin.member.model.dto.MemberDTO;
import com.ohgiraffers.dosirak.admin.order.model.dto.OrderDTO;
import com.ohgiraffers.dosirak.admin.order.model.dto.PayDTO;
import com.ohgiraffers.dosirak.admin.product.dto.productDTO;
import com.ohgiraffers.dosirak.user.order.model.dao.CartMapper;
import com.ohgiraffers.dosirak.user.order.model.dto.CartDTO;
import com.ohgiraffers.dosirak.user.product.dto.ProductUserDTO;
import com.ohgiraffers.dosirak.user.suitBox.model.dto.MenuDTO;
import com.ohgiraffers.dosirak.user.suitBox.model.dto.SuitBoxDTO;
import com.ohgiraffers.dosirak.user.suitBox.model.dto.SuitBoxMenuDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@org.springframework.stereotype.Service
@Transactional
public class CartService {

    private final CartMapper cartMapper;

    public CartService(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    public List<CartDTO> userCartList(String userId) {
        return cartMapper.userCartList(userId);
    }

    public void cartUpdateQuantity(CartDTO cartDTO) {

        if (cartDTO != null) {
            int productCode = cartDTO.getProductCode();
            int updatedQuantity = cartDTO.getCartitemCount();

            cartMapper.cartUpdateQuantity(productCode, updatedQuantity);
        } else {
            System.out.println("productDTO가 null입니다. 요청을 확인하세요.");
        }
    }

    public List<MemberDTO> findSearchId() {
        return cartMapper.findSearchId();
    }

    public String findOrderCode() {
        return cartMapper.findOrderCode();
    }

    public PayDTO findPayDate(String orderCode) {
        return cartMapper.findPayDate(orderCode);
    }

    public MenuDTO getMenuByCode(int menuCode) {
        return cartMapper.getMenuByCode(menuCode);
    }

    public void cartUpdateQuantitySuitbox(Map<String, String> suitBox) {
        if (suitBox != null) {
            cartMapper.cartUpdateQuantitySuitbox(suitBox);
        } else {
            System.out.println("productDTO가 null입니다. 요청을 확인하세요.");
        }

    }

    public MemberDTO getPaymentByUserId(String memberId) {
        return cartMapper.getPaymentByUserId(memberId);
    }

    public CartDTO getProductByCart(Map<String, String> productAndId) {
        return cartMapper.getProductByCart(productAndId);
    }

    public CartDTO getSuitboxCodeByCart(Map<String, String> suitboxCodeAndId) {
        return cartMapper.getSuitboxCodeByCart(suitboxCodeAndId);
    }

    public List<CartDTO> divisionProduct(List<CartDTO> cartList) {
        for (CartDTO cart : cartList) {
            SuitBoxDTO suitBox = cart.getSuitbox();
            if (suitBox != null) {  // suitBox 분류
                cart.setSuitboxCode(suitBox.getSuitboxCode());  // 맞춤도시락, 메뉴 코드 저장
                int riceCode = suitBox.getRiceCode();
                int mainCode = suitBox.getMainCode();
                int sideCode = suitBox.getSideCode();
                int kimchiCode = suitBox.getKimchiCode();

                SuitBoxMenuDTO detailMenu = new SuitBoxMenuDTO();   //메뉴별 정보 끌어오기
                detailMenu.setSuitboxCode(suitBox.getSuitboxCode());
                detailMenu.setRice(getMenuByCode(riceCode));
                detailMenu.setMain(getMenuByCode(mainCode));
                detailMenu.setSide(getMenuByCode(sideCode));
                detailMenu.setKimchi(getMenuByCode(kimchiCode));
                cart.setDetailSuitBox(detailMenu);
                cart.setProductPrice(7000 + // 가격 설정하기
                        detailMenu.getRice().getMenuExtracash() +
                        detailMenu.getMain().getMenuExtracash() +
                        detailMenu.getSide().getMenuExtracash() +
                        detailMenu.getKimchi().getMenuExtracash());
            }

        }

        return cartList;
    }

    public List<CartDTO> setCartDTO(Map<String, String> productAndQuantity, String memberId) {
        List<CartDTO> cartList = new ArrayList<>();
        for (String key : productAndQuantity.keySet()) {
            if (key.contains("product")) {
                String productCode = productAndQuantity.get(key);
                Map<String, String> productAndId = new HashMap<>();
                productAndId.put("userId", memberId);
                productAndId.put("productCode", productCode);
                cartList.add(getProductByCart(productAndId));
            } else if (key.contains("suitbox")) {
                String suitboxCode = productAndQuantity.get(key);
                Map<String, String> suitboxCodeAndId = new HashMap<>();
                suitboxCodeAndId.put("userId", memberId);
                suitboxCodeAndId.put("suitboxCode", suitboxCode);
                cartList.add(getSuitboxCodeByCart(suitboxCodeAndId));
            }
        }
        return cartList;
    }

    public void insertDetailSuitbox(CartDTO cart) {
        cartMapper.insertDetailSuitbox(cart);
    }

    public void insertDetailProduct(CartDTO cart) {
        cartMapper.insertDetailProduct(cart);
    }

    public int insertPay(Map<String, String> pay) {
        return cartMapper.insertPay(pay);
    }

    public int insertDelivery(String orderCode) {
        return cartMapper.insertDelivery(orderCode);
    }

    public void deleteCartSuitbox(String itemCode, String memberId) {
        cartMapper.deleteCartSuitbox(itemCode, memberId);
    }

    public void deleteCartProduct(String itemCode, String memberId) {
        cartMapper.deleteCartProduct(itemCode, memberId);
    }

    public void cartDeleteProductList(CartDTO productCode) {
        cartMapper.cartDeleteProductList(productCode);
    }

    public void cartDeleteSuitboxList(CartDTO suitboxCode) {
        cartMapper.cartDeleteSuitboxList(suitboxCode);
    }

    public int userOrderDone(String memberId, String name, String phone, String address1, String address2, String address3) {
        return cartMapper.userOrderDone(memberId, name, phone, address1, address2, address3);
    }
}
