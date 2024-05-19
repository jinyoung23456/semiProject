package com.ohgiraffers.dosirak.user.join.model.service;

import com.ohgiraffers.dosirak.admin.member.model.dto.MemberDTO;
import com.ohgiraffers.dosirak.common.member.MemberRegistException;
import com.ohgiraffers.dosirak.user.join.model.dao.JoinMapper;
//import com.ohgiraffers.dosirak.user.join.model.dto.JoinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JoinService {

    @Autowired
    private JoinMapper joinMapper;

    public boolean idCheck(String id) {
        String result = joinMapper.idCheck(id);
        return result != null;
    }

    @Transactional
    public void registMember(MemberDTO member) throws MemberRegistException {
        int registResult = joinMapper.registMember(member);
        if(!(registResult>0)) throw new MemberRegistException("회원가입 실패");
    }
}
