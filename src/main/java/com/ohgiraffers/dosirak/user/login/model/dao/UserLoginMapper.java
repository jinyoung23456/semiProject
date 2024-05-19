package com.ohgiraffers.dosirak.user.login.model.dao;

import com.ohgiraffers.dosirak.admin.member.model.dto.MemberDTO;
import com.ohgiraffers.dosirak.user.login.model.dto.LoginDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLoginMapper {

    LoginDTO findById(String id);

    MemberDTO findIDform(MemberDTO member);

    MemberDTO findPWDform(MemberDTO member);

    int pwdResetAdmin(String encodePwd, String id);

    int pwdResetUser(String encodePwd, String id);
}
