package com.ohgiraffers.dosirak.admin.customer.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ImgDTO {

    private int imgCode;
    private int refAskCode;
    private String originalName;
    private String savedName;
    private String savePath;
    private String fileType;
    private String thumbNailPath;
    private String attachmentStatus;

    public ImgDTO() {};

    public ImgDTO(int imgCode, int refAskCode, String originalName, String savedName, String savePath, String fileType, String thumbNailPath, String attachmentStatus) {
        this.imgCode = imgCode;
        this.refAskCode = refAskCode;
        this.originalName = originalName;
        this.savedName = savedName;
        this.savePath = savePath;
        this.fileType = fileType;
        this.thumbNailPath = thumbNailPath;
        this.attachmentStatus = attachmentStatus;
    }
}
