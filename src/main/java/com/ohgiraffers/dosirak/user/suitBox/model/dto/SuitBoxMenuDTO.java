package com.ohgiraffers.dosirak.user.suitBox.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SuitBoxMenuDTO {
    private int suitboxCode;
    private MenuDTO rice;
    private MenuDTO main;
    private MenuDTO side;
    private MenuDTO kimchi;

    public SuitBoxMenuDTO() {
    }

    public SuitBoxMenuDTO(int suitboxCode, MenuDTO rice, MenuDTO main, MenuDTO side, MenuDTO kimchi) {
        this.suitboxCode = suitboxCode;
        this.rice = rice;
        this.main = main;
        this.side = side;
        this.kimchi = kimchi;
    }
}
