package com.ohgiraffers.dosirak.user.product.dao;

import com.ohgiraffers.dosirak.admin.product.dto.ProductImageDTO;
import com.ohgiraffers.dosirak.admin.product.dto.productDTO;
import com.ohgiraffers.dosirak.user.order.model.dto.CartDTO;
import com.ohgiraffers.dosirak.user.product.dto.ProductUserDTO;
import com.ohgiraffers.dosirak.user.product.dto.ProductandImageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductUserMapper {
    List<productDTO> findAllUserProduct();

    ProductUserDTO viewProduct(int productCode);

    ProductUserDTO productsListViewNomal(int categoryCode);

    ProductUserDTO productsListView();

    ProductUserDTO productsListView3();


    List<ProductUserDTO> getProductListBySubCategoryCode(int subCategoryCode);

    int addCart(Map<String, String> productInfo);
    List<ProductandImageDTO> comeImageList();

    List<ProductUserDTO> findAllProducts();

    List<ProductImageDTO> findImagesByProductCode(int productCode);

    List<ProductUserDTO> viewProduct2(int productCode);

    List<ProductImageDTO> searchImageForProduct(int productCode);
}
