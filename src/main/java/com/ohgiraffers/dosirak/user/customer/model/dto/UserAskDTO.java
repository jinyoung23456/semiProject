package com.ohgiraffers.dosirak.user.customer.model.dto;

import com.ohgiraffers.dosirak.admin.customer.model.dto.AnswerDTO;
import com.ohgiraffers.dosirak.admin.customer.model.dto.AskCategoryDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class UserAskDTO {

    private int askCode;
    private String askTitle;
    private String askContent;
    private String askDate;
    private String userId;
    private String editDate;
    private Boolean askDelete;
    private int askCategoryCode; // 추후 수정

    private UserAnswerDTO answerDTO;
    private UserAskCategoryDTO askCategoryDTO;
    private List<UserCustomerImgDTO> imageList;

    public UserAskDTO() {}

    public UserAskDTO(int askCode, String askTitle, String askContent, String askDate, String userId, String editDate, Boolean askDelete, int askCategoryCode, UserAnswerDTO answerDTO, UserAskCategoryDTO askCategoryDTO, List<UserCustomerImgDTO> imageList) {
        this.askCode = askCode;
        this.askTitle = askTitle;
        this.askContent = askContent;
        this.askDate = askDate;
        this.userId = userId;
        this.editDate = editDate;
        this.askDelete = askDelete;
        this.askCategoryCode = askCategoryCode;
        this.answerDTO = answerDTO;
        this.askCategoryDTO = askCategoryDTO;
        this.imageList = imageList;
    }
}
