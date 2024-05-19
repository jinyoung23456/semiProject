package com.ohgiraffers.dosirak.user.myInfo.model.service;

import com.ohgiraffers.dosirak.admin.member.model.dto.MemberDTO;
import com.ohgiraffers.dosirak.common.member.MemberModifyException;
import com.ohgiraffers.dosirak.user.myInfo.model.dao.MyinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyinfoService {

    @Autowired
    private MyinfoMapper myinfoMapper;

    public MemberDTO myinfoSelect(String id) {
        return myinfoMapper.myinfoSelect(id);
    }

    @Transactional
    public void modifyMyinfo(MemberDTO member) throws MemberModifyException {
        int result = myinfoMapper.modifyMyinfo(member);
        if(!(result>0)) throw new MemberModifyException("회원 정보 수정 실패");
    }

    public void withdrawalMember(MemberDTO member) throws MemberModifyException {
        int result = myinfoMapper.withdrawalMember(member);
        if(!(result>0)) throw new MemberModifyException("회원 탈퇴 실패");
    }

    public boolean emailDupCheck(String email) {
        String result = myinfoMapper.emailDupCheck(email);
        return result != null;
    }
}
