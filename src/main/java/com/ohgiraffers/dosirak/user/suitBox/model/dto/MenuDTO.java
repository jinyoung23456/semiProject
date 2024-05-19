package com.ohgiraffers.dosirak.user.suitBox.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MenuDTO {
    private int menuCode;
    private String menuName;
    private String menuCategory;
    private int menuExtracash;
    private String menuStatus;


    public MenuDTO() {
    }

    public MenuDTO(int menuCode, String menuName, String menuCategory, int menuExtracash, String menuStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuCategory = menuCategory;
        this.menuExtracash = menuExtracash;
        this.menuStatus = menuStatus;
    }
}
