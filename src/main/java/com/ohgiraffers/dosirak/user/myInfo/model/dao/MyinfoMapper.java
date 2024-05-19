package com.ohgiraffers.dosirak.user.myInfo.model.dao;

import com.ohgiraffers.dosirak.admin.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyinfoMapper {
    MemberDTO myinfoSelect(String id);

    int modifyMyinfo(MemberDTO member);

    int withdrawalMember(MemberDTO member);

    MemberDTO pwdCheckForMyinfo(MemberDTO member);

    String emailDupCheck(String email);
}
