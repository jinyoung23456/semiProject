package com.ohgiraffers.dosirak.admin.product.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ProductImageDTO {

    private int imgCode;
    private int productCode;
    private String savedName;
    private String savePath;

    public ProductImageDTO() {}

    public ProductImageDTO(int imgCode, int productCode, String savedName, String savePath) {
        this.imgCode = imgCode;
        this.productCode = productCode;
        this.savedName = savedName;
        this.savePath = savePath;
    }

    public int getImgCode() {
        return imgCode;
    }

    public void setImgCode(int imgCode) {
        this.imgCode = imgCode;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getSavedName() {
        return savedName;
    }

    public void setSavedName(String savedName) {
        this.savedName = savedName;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    @Override
    public String toString() {
        return "ProductImageDTO{" +
                "imgCode=" + imgCode +
                ", productCode=" + productCode +
                ", savedName='" + savedName + '\'' +
                ", savePath='" + savePath + '\'' +
                '}';
    }
}
