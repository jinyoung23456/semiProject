package com.ohgiraffers.dosirak.admin.product.dao;

import com.ohgiraffers.dosirak.admin.product.dto.ProductImageDTO;
import com.ohgiraffers.dosirak.admin.product.dto.productDTO;
import com.ohgiraffers.dosirak.user.product.dto.ProductandImageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface ProductMapper {
    List<productDTO> findAllProduct();
    

    int insertProduction(productDTO product);


    List<productDTO> productSelectList(String key);


    productDTO getProductByCode(int productCode);


    int productUpdate(productDTO product);

    void insertImage(ProductImageDTO fileInfo);


    productDTO codePlz();

    void deleteProductById(int productCode);

    List<productDTO> filterByStatus(String status);

    List<productDTO> searchByKeyword(String keyword);

    List<productDTO> findByProductStatusAndProductNameContaining(String status, String name);

    List<productDTO> findByProductStatus(String status);

    List<productDTO> findByProductNameContaining(String name);

    List<productDTO> findAll();


    List<productDTO> findByCriteria(String productName, int productCategoryCode, String productStatus);
}
