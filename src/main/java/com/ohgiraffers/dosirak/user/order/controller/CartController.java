package com.ohgiraffers.dosirak.user.order.controller;

import com.ohgiraffers.dosirak.admin.login.model.AdminLoginDetails;
import com.ohgiraffers.dosirak.admin.member.model.dto.MemberDTO;
import com.ohgiraffers.dosirak.admin.order.model.dto.OrderDTO;
import com.ohgiraffers.dosirak.admin.order.model.dto.PayDTO;
import com.ohgiraffers.dosirak.admin.product.dto.productDTO;
import com.ohgiraffers.dosirak.user.login.model.dto.LoginDTO;
import com.ohgiraffers.dosirak.user.order.model.dto.CartDTO;
import com.ohgiraffers.dosirak.user.order.model.service.CartService;
import com.ohgiraffers.dosirak.user.product.dto.ProductUserDTO;
import com.ohgiraffers.dosirak.user.suitBox.model.dto.SuitBoxDTO;
import com.ohgiraffers.dosirak.user.suitBox.model.dto.SuitBoxMenuDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user/*")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("cart")
    public String cart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || !authentication.isAuthenticated()) {      // 비로그인 장바구니 이동 시 리디렉션
//            response.sendRedirect("/login");
//            return null;
//        }
        String memberId = "";
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof AdminLoginDetails) {
                AdminLoginDetails adminLoginDetails = (AdminLoginDetails) principal;
                LoginDTO login = adminLoginDetails.getLoginDTO();
                memberId = login.getId();
                model.addAttribute("memberId", memberId);
            }
        }
        List<CartDTO> cartDTO = cartService.userCartList(memberId);
        cartDTO = cartService.divisionProduct(cartDTO);
        model.addAttribute("cartDTO", cartDTO);

        return "/user/order/cart";
    }

    @PostMapping("payment")
    public String payment(Model model, @RequestParam Map<String, String> productAndQuantity) {
        String memberId = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof AdminLoginDetails) {
                AdminLoginDetails adminLoginDetails = (AdminLoginDetails) principal;
                LoginDTO login = adminLoginDetails.getLoginDTO();
                memberId = login.getId();

                model.addAttribute("memberId", memberId);
                productAndQuantity.put("memberId", memberId);

                MemberDTO user = cartService.getPaymentByUserId(memberId);
                model.addAttribute("user", user);
            }
        }
//        key 에 contain(suitbox) 되면 맞춤도시락 (product) 면 일반상품
//        value = 일반 또는 맞춤도시락 코드
        List<CartDTO> cartList = new ArrayList<>();

        cartList = cartService.setCartDTO(productAndQuantity, memberId);

        cartList = cartService.divisionProduct(cartList);

        model.addAttribute("cartList", cartList);

        return "/user/order/payment";
    }

    @PostMapping("orderDone")
    public String orderDone(Model model, @RequestParam Map<String, String> codeMap,
                            MemberDTO memberDTO,
                            @RequestParam String payMethod) {

        String resultUrl = "/user/order/orderFail";

        String memberId = "";

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();
            if(principal instanceof AdminLoginDetails){
                AdminLoginDetails adminLoginDetails = (AdminLoginDetails) principal;
                LoginDTO login = adminLoginDetails.getLoginDTO();
                memberId = login.getId();
                memberDTO.setId(memberId);
            }
        }

        String name = codeMap.get("name");
        String phone = codeMap.get("phone");
        String address1 = codeMap.get("address1");
        String address2 = codeMap.get("address2");
        String address3 = codeMap.get("address3");

        int createOrderTb = cartService.userOrderDone(memberId, name, phone, address1, address2, address3);

        if (createOrderTb > 0) {
            String orderCode = cartService.findOrderCode();
            int payPrice = 0;
            List<CartDTO> cartList = new ArrayList<>();

            cartList = cartService.setCartDTO(codeMap, memberId);   //codeMap = 상품, 맞춤도시락 코드

            cartList = cartService.divisionProduct(cartList);   // 맞춤도시락 상세 정보, 가격 가져오기

            for(CartDTO cart : cartList){
                cart.setOrderCode(orderCode);
                if (cart.getProductCode() == 0){
                    cartService.insertDetailSuitbox(cart);  // 맞춤도시락 INSERT
                } else {
                    cartService.insertDetailProduct(cart);  // 일반상품 INSERT
                }
                payPrice = payPrice + cart.getProductPrice() * cart.getCartitemCount();
            }

            if (payPrice < 30000) {
                payPrice += 3000;
            }

            Map<String, String> pay = new HashMap<>();
            pay.put("orderCode", orderCode);
            pay.put("payPrice", String.valueOf(payPrice));
            pay.put("payStatus", "O");
            pay.put("payMethod", payMethod);
            int payInputResult = cartService.insertPay(pay);
            int deliveryResult = cartService.insertDelivery(orderCode);
            for(CartDTO cart : cartList){
                if (cart.getProductCode() == 0){
                    String itemCode = String.valueOf(cart.getSuitboxCode());
                    cartService.deleteCartSuitbox(itemCode, memberId);  // 맞춤도시락 INSERT
                } else {
                    String itemCode = String.valueOf(cart.getProductCode());
                    cartService.deleteCartProduct(itemCode, memberId);  // 일반상품 INSERT
                }
                payPrice = payPrice + cart.getProductPrice() * cart.getCartitemCount();
            }

            PayDTO payDTO = cartService.findPayDate(orderCode);
            model.addAttribute("orderCode", orderCode);
            model.addAttribute("payDTO", payDTO);

        }



        return "/user/order/orderDone";
    }
}
