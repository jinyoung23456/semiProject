package com.ohgiraffers.dosirak.admin.suitBox.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SuitBoxMenuDTO {
    private int menuCode;
    private String menuName;
    private String menuCategory;
    private int menuExtracash;
    private char menuStatus;
    private float menuCarbo;
    private float menuSugar;
    private float menuProtein;
    private float menuFat;
    private float menuSaturatedFat;
    private float menuTransFat;
    private float menuCholesterol;
    private float menuNatrium;
    private int menuCalory;
}
