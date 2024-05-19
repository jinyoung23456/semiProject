package com.ohgiraffers.dosirak.admin.login.model;

import com.ohgiraffers.dosirak.user.login.model.dto.LoginDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class AdminLoginDetails implements UserDetails {
    private LoginDTO loginDTO;
    public AdminLoginDetails(){}
    public AdminLoginDetails(LoginDTO loginDTO){
        this.loginDTO = loginDTO;
    }
    public LoginDTO getLoginDTO(){return loginDTO;}
    public void setLoginDTO(LoginDTO loginDTO){
        this.loginDTO = loginDTO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        loginDTO.getRole().forEach(role -> authorities.add(() -> role));

        return authorities;
    }

    @Override
    public String getPassword() {
        return loginDTO.getPwd();
    }

    @Override
    public String getUsername() {
        return loginDTO.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
