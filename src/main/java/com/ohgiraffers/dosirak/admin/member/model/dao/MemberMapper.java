package com.ohgiraffers.dosirak.admin.member.model.dao;

import com.ohgiraffers.dosirak.admin.member.model.dto.ManagerDTO;
import com.ohgiraffers.dosirak.admin.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {

    List<MemberDTO> findAllMember();

    List<ManagerDTO> findAllManager();

    MemberDTO selectMemberView(String id);

    int modifyMember(MemberDTO member);

    ManagerDTO selectManagerView(String id);

    int modifyManager(ManagerDTO manager);

    String checkDuplication(String id);

    int registManager(ManagerDTO manager);

    int memberPwdReset(MemberDTO member);

    int managerPwdReset(ManagerDTO manager);

    List<ManagerDTO> searchManager(String condition, String value);

    List<MemberDTO> searchMemberForm(String condition, String value);

    List<ManagerDTO> searchManagerForm(String condition, String value);
}
