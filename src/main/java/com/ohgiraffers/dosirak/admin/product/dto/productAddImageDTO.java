package com.ohgiraffers.dosirak.admin.product.dto;

import com.ohgiraffers.dosirak.admin.customer.model.dto.AnswerDTO;
import com.ohgiraffers.dosirak.user.customer.model.dto.UserAskCategoryDTO;
import com.ohgiraffers.dosirak.user.customer.model.dto.UserCustomerImgDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@ToString
public class productAddImageDTO {
    private String productName;
    private int productCode;
    private int productPrice;
    private String productStatus;
    private String productSummary;
    private int productCategoryCode;
    private List<UserCustomerImgDTO> imageList;

}
