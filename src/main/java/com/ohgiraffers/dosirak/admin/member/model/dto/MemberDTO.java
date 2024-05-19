package com.ohgiraffers.dosirak.admin.member.model.dto;

public class MemberDTO {
    private String id;
    private String name;
    private String birth;
    private char gender;
    private String phone;
    private String email;
    private String address1;
    private String address2;
    private String address3;
    private String agree;
    private String pwd;
    private String joindate;
    private boolean withdrawal;
    private String role;
    public MemberDTO(){}

    public MemberDTO(String id, String name, String birth, char gender, String phone, String email, String address1, String address2, String address3, String agree, String pwd, String joindate, boolean withdrawal, String role) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.agree = agree;
        this.pwd = pwd;
        this.joindate = joindate;
        this.withdrawal = withdrawal;
        this.role = role;
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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getJoindate() {
        return joindate;
    }

    public void setJoindate(String joindate) {
        this.joindate = joindate;
    }

    public boolean isWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(boolean withdrawal) {
        this.withdrawal = withdrawal;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birth='" + birth + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", address3='" + address3 + '\'' +
                ", agree='" + agree + '\'' +
                ", pwd='" + pwd + '\'' +
                ", joindate='" + joindate + '\'' +
                ", withdrawal=" + withdrawal +
                ", role='" + role + '\'' +
                '}';
    }
}
