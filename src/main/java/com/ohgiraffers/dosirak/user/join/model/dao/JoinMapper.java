package com.ohgiraffers.dosirak.user.join.model.dao;

import com.ohgiraffers.dosirak.admin.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JoinMapper {

    String idCheck(String id);

    int registMember(MemberDTO member);
}
