package com.ohgiraffers.dosirak.admin.login.model.service;

import com.ohgiraffers.dosirak.admin.login.model.AdminLoginDetails;
import com.ohgiraffers.dosirak.user.login.model.dto.LoginDTO;
import com.ohgiraffers.dosirak.user.login.model.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AdminLoginService implements UserDetailsService {

    @Autowired
    private UserLoginService userLoginService;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        LoginDTO login = userLoginService.findById(id);

        if(Objects.isNull(login)){
            throw new UsernameNotFoundException("해당하는 회원 정보 없음");
        }

        return new AdminLoginDetails(login);
    }
}
