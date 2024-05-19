package com.ohgiraffers.dosirak.common.email.model.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailMapper {
    int saveVerificationCode(String hiddenCode, String verificationCode);

    int verificationService(String inputCode, String hiddenCode);
}
