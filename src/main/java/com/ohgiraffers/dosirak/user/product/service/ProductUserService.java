package com.ohgiraffers.dosirak.user.product.service;

import com.ohgiraffers.dosirak.admin.product.dto.ProductImageDTO;
import com.ohgiraffers.dosirak.admin.product.dto.productDTO;
import com.ohgiraffers.dosirak.user.order.model.dao.CartMapper;
import com.ohgiraffers.dosirak.user.product.dao.ProductUserMapper;
import com.ohgiraffers.dosirak.user.product.dto.ProductUserDTO;
import com.ohgiraffers.dosirak.user.product.dto.ProductandImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductUserService {
    @Autowired
    private ProductUserMapper productUserMapper;
    @Autowired
    private CartMapper cartMapper;

    public List<productDTO> findAllProduct() {
        return productUserMapper.findAllUserProduct();
    }

    public ProductUserDTO viewProduct(int productCode) {
        ProductUserDTO product= productUserMapper.viewProduct(productCode);
            // 상품 코드를 기반으로 이미지 리스트 가져오기
        if (product != null) {
            // 상품 코드를 기반으로 이미지 리스트 가져오기
            List<ProductImageDTO> images = productUserMapper.findImagesByProductCode(productCode);
            // 이미지 리스트를 상품 DTO에 설정하기

            product.setImageList(images);
        }
        return product;

    }


    public ProductUserDTO productsListViewNomal(int categoryCode) {
        return productUserMapper.productsListViewNomal(categoryCode);
    }

    public ProductUserDTO productsListView() {
        return productUserMapper.productsListView();
    }


    public List<ProductUserDTO> getProductListBySubCategoryCode(int subCategoryCode) {
        System.out.println(subCategoryCode);
        return productUserMapper.getProductListBySubCategoryCode(subCategoryCode);
    }


    public int addCart(Map<String, String> productInfo) {
        return productUserMapper.addCart(productInfo);
    }

    public List<ProductandImageDTO> comeImageList() {
        return productUserMapper.comeImageList();
    }

    public List<ProductUserDTO> getProductsWithImages() {
        List<ProductUserDTO> products = productUserMapper.findAllProducts();
        for (ProductUserDTO product : products) {
            // 상품 코드를 기반으로 이미지 리스트 가져오기
            List<ProductImageDTO> images = productUserMapper.findImagesByProductCode(product.getProductCode());
            // 이미지 리스트를 상품 DTO에 설정하기
            product.setImageList(images);
        }
        return products;
    }


    public List<ProductUserDTO> getViewProductsWithImages(int productCode) {
        List<ProductUserDTO> products = productUserMapper.viewProduct2(productCode);

        for (ProductUserDTO product : products) {
            // 상품 코드를 기반으로 이미지 리스트 가져오기
            List<ProductImageDTO> images = productUserMapper.findImagesByProductCode(product.getProductCode());
            // 이미지 리스트를 상품 DTO에 설정하기
            product.setImageList(images);


        }
        return products;

    }

    @Transactional(readOnly = true)
    public List<String> searchImageForProduct(int productCode) {
        List<String> imageList = new ArrayList<>();

        List<ProductImageDTO> productImages = productUserMapper.searchImageForProduct(productCode);
        System.out.println("out:"+productImages);
        // 조회된 이미지 정보에서 저장 이름만 추출하여 imageList에 추가
        for (ProductImageDTO productImage : productImages) {
            imageList.add(productImage.getSavedName());


    }            return imageList;

    }
}
