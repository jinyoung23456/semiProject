package com.ohgiraffers.dosirak.common.email.model.service;

import com.ohgiraffers.dosirak.common.email.model.dao.EmailMapper;
import com.ohgiraffers.dosirak.common.member.MemberRegistException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmailService {
    private JavaMailSender emailSender;
    private EmailMapper emailMapper;
    public EmailService(JavaMailSender emailSender, EmailMapper emailMapper){
        this.emailSender = emailSender;
        this.emailMapper = emailMapper;
    }

    public void sendVerificationCode(String to, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("[도시락] 인증코드 발송");
        message.setText("인증코드 : " + verificationCode);
        emailSender.send(message);
    }

    @Transactional
    public void saveVerificationCode(String hiddenCode, String verificationCode) throws MemberRegistException {
        int result = emailMapper.saveVerificationCode(hiddenCode, verificationCode);
        if(!(result>0)) throw new MemberRegistException("인증번호 저장 실패");
    }

    public boolean verifyVerificationCode(String inputCode, String hiddenCode) {
        int result = emailMapper.verificationService(inputCode, hiddenCode);
        return result > 0;
    }
}
