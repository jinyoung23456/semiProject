package com.ohgiraffers.dosirak.common.email.controller;

import com.ohgiraffers.dosirak.common.email.model.service.EmailService;
import com.ohgiraffers.dosirak.common.member.MemberRegistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendVerificationCode")
    public void sendVerificationCode(@RequestBody Map<String, String> request) throws MemberRegistException {
        String email = request.get("email");
        String hiddenCode = request.get("hiddenCode");

        String verificationCode = generateVerificationCode();
        emailService.sendVerificationCode(email, verificationCode);
        emailService.saveVerificationCode(hiddenCode, verificationCode);
    }

    private String generateVerificationCode(){
        SecureRandom random = new SecureRandom();
        BigInteger bigInteger = new BigInteger(128, random);
        return bigInteger.toString(16);
    }

    @PostMapping("/verifyVerificationCode")
    public ResponseEntity<?> verifyVerificationCode(@RequestBody Map<String, String> request){
        String inputCode = request.get("inputCode");
        String hiddenCode = request.get("hiddenCode");

        boolean isValid = emailService.verifyVerificationCode(inputCode, hiddenCode);

        Map<String, Boolean> response = new HashMap<>();
        response.put("valid", isValid);

        return ResponseEntity.ok(response);
    }

}
