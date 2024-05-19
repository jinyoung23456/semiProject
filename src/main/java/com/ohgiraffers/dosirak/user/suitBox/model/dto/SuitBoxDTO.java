package com.ohgiraffers.dosirak.user.suitBox.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SuitBoxDTO {
    private int suitboxCode;
    private int riceCode;
    private int mainCode;
    private int sideCode;
    private int kimchiCode;

    public SuitBoxDTO() {
    }

    public SuitBoxDTO(int suitboxCode, int riceCode, int mainCode, int sideCode, int kimchiCode) {
        this.suitboxCode = suitboxCode;
        this.riceCode = riceCode;
        this.mainCode = mainCode;
        this.sideCode = sideCode;
        this.kimchiCode = kimchiCode;
    }
}
