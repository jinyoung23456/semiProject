package com.ohgiraffers.dosirak.user.login.model.dto;

import com.ohgiraffers.dosirak.common.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginDTO implements java.io.Serializable {

    private String id;
    private String name;
    private String pwd;
    private String authority;
    private UserRole role;

    public LoginDTO() {}

    public LoginDTO(String id, String name, String pwd, String authority, UserRole role) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.authority = authority;
        this.role = role;
    }

    public List<String> getRole() {
        if(this.role.getRole().length() > 0) {
            return Arrays.asList(this.role.getRole().split(","));
        }

        return new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", authority='" + authority + '\'' +
                ", role=" + role +
                '}';
    }
}
