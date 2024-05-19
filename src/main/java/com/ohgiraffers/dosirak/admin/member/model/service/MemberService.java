package com.ohgiraffers.dosirak.admin.member.model.service;

import com.ohgiraffers.dosirak.admin.member.model.dao.MemberMapper;
import com.ohgiraffers.dosirak.admin.member.model.dto.ManagerDTO;
import com.ohgiraffers.dosirak.admin.member.model.dto.MemberDTO;
import com.ohgiraffers.dosirak.common.member.MemberModifyException;
import com.ohgiraffers.dosirak.common.member.MemberRegistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    public List<MemberDTO> findAllMember(){
        return memberMapper.findAllMember();
    }

    public MemberDTO selectMemberView(String id) {
        return memberMapper.selectMemberView(id);
    }

    @Transactional
    public void modifyMember(MemberDTO member) throws MemberModifyException {
        int result = memberMapper.modifyMember(member);
        if(!(result>0)) throw new MemberModifyException("회원 정보 수정 실패");
    }

    public List<ManagerDTO> findAllManager() {
        return memberMapper.findAllManager();
    }

    public ManagerDTO selectManagerView(String id) {
        return memberMapper.selectManagerView(id);
    }

    @Transactional
    public void modifyManager(ManagerDTO manager) throws MemberModifyException {
        int result = memberMapper.modifyManager(manager);
        if(!(result>0)) throw new MemberModifyException("관리자 정보 수정 실패");
    }

    public boolean checkDuplication(String id) {
        String result = memberMapper.checkDuplication(id);
        return result != null;
    }

    @Transactional
    public void registManager(ManagerDTO manager) throws MemberRegistException {
        int registResult = memberMapper.registManager(manager);
        if(!(registResult>0)) throw new MemberRegistException("회원가입 실패");
    }

    @Transactional
    public void memberPwdReset(MemberDTO member) throws MemberModifyException {
        int resetResult = memberMapper.memberPwdReset(member);
        if(!(resetResult>0)) throw new MemberModifyException("비밀번호 초기화 실패");
    }

    @Transactional
    public void managerPwdReset(ManagerDTO manager) throws MemberModifyException {
        int resetResult = memberMapper.managerPwdReset(manager);
        if(!(resetResult>0)) throw new MemberModifyException("비밀번호 초기화 실패");
    }

    public List<ManagerDTO> searchManager(String condition, String value) {
        return memberMapper.searchManager(condition, value);
    }

    public List<MemberDTO> searchMemberForm(String condition, String value) {
        return memberMapper.searchMemberForm(condition, value);
    }

    public List<ManagerDTO> searchManagerForm(String condition, String value) {
        return memberMapper.searchManagerForm(condition, value);
    }
}
