package com.ohgiraffers.dosirak.admin.customer.model.dto;

import com.ohgiraffers.dosirak.user.customer.model.dto.UserCustomerImgDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class AskDTO {

    private int askCode;
    private String askTitle;
    private String askContent;
    private String askDate;
    private String userId;
    private String editDate;
    private Boolean askDelete;

    private AskCategoryDTO askCategoryDTO; //추후 수정
    private AnswerDTO answerDTO;
    private ImgDTO imgDTO;
    
    private List<UserCustomerImgDTO> imageList;

}
