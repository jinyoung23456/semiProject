package com.ohgiraffers.dosirak.user.product.controller;


import com.ohgiraffers.dosirak.admin.login.model.AdminLoginDetails;
import com.ohgiraffers.dosirak.admin.member.model.dto.ManagerDTO;
import com.ohgiraffers.dosirak.admin.product.dto.ProductImageDTO;
import com.ohgiraffers.dosirak.admin.product.dto.productDTO;
import com.ohgiraffers.dosirak.user.login.model.dto.LoginDTO;
import com.ohgiraffers.dosirak.user.order.model.dto.CartDTO;
import com.ohgiraffers.dosirak.user.product.dto.ProductUserDTO;
import com.ohgiraffers.dosirak.user.product.dto.ProductandImageDTO;
import com.ohgiraffers.dosirak.user.product.service.ProductUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/user/product/*")

public class ProductUserController {

    private final ProductUserService productUserService;


    @Autowired
    public ProductUserController(ProductUserService productUserService) {
        this.productUserService = productUserService;
    }

    @GetMapping("/productList")
    public String productList(Model model) {
        List<productDTO> productList1 = productUserService.findAllProduct();
        List<ProductUserDTO> productList = productUserService.getProductsWithImages();
        model.addAttribute("productList", productList);

//        List<ProductandImageDTO> imageList=productUserService.comeImageList();
//        model.addAttribute("imageList", imageList);


        return "/user/product/productList";
    }

    @GetMapping("/productView")
    public String productView(@RequestParam int productCode, Model model) {
        System.out.println(productCode);

        ProductUserDTO productList = productUserService.viewProduct(productCode);
        if (productList != null && productList.getImageList() != null) {
            List<ProductImageDTO> imageList = productList.getImageList();

            // 첫 번째 이미지 처리
            if (!imageList.isEmpty()) {
                ProductImageDTO firstImage = imageList.get(0);
                String firstSavedName = firstImage.getSavedName();
                model.addAttribute("firstSavedName", firstSavedName);
                System.out.println("f"+firstSavedName);
            }

            // 나머지 이미지 처리
            List<ProductImageDTO> remainingImages = new ArrayList<>();
            if (imageList.size() > 1) {
                remainingImages.addAll(imageList.subList(1, imageList.size()));
                System.out.println("r"+remainingImages);

            }

            model.addAttribute("remainingImages", remainingImages);
            model.addAttribute("productList",productList);

        }
        return "/user/product/productUserView";
    }
//
    @GetMapping("/productListJungsung")
    public String productListJungsung(Model model) {
        List<ProductUserDTO> productList = productUserService.getProductListBySubCategoryCode(2);
        model.addAttribute("productList", productList);
        return "/user/product/productList";
    }
//
    @GetMapping("/productListHel")
    public String productListHel(Model model) {
        List<ProductUserDTO> productList = productUserService.getProductListBySubCategoryCode(1);
        model.addAttribute("productList", productList);
        return "/user/product/productList";
    }

    @GetMapping("/productListComp")
    public String productListComp(Model model) {
        List<ProductUserDTO> productList = productUserService.getProductListBySubCategoryCode(3);
        model.addAttribute("productList", productList);
        return "/user/product/productList";
    }

    @PostMapping("/add-to-cart")
    public @ResponseBody String addToCart(@RequestBody Map<String, String> productInfo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String managerAuthor="";
        String userId="";
        if(authentication != null && authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();

            if(principal instanceof AdminLoginDetails){
                AdminLoginDetails adminLoginDetails = (AdminLoginDetails) principal;
                LoginDTO login = adminLoginDetails.getLoginDTO();
                managerAuthor = login.getAuthority();
                userId = login.getId();
            }
        }
        productInfo.put("userId", userId);
        System.out.println(productInfo);

        int result=productUserService.addCart(productInfo);


        return "/user/order/cart";
    }

}
