package com.ohgiraffers.dosirak.admin.member.model.dto;

public class ManagerDTO {
    private String id;
    private String name;
    private String birth;
    private char gender;
    private String contact;
    private String email;
    private String position;
    private String department;
    private char authority;
    private String pwd;
    private String joindate;
    private boolean withdrawal;
    private String role;
    public ManagerDTO(){}

    public ManagerDTO(String id, String name, String birth, char gender, String contact, String email, String position, String department, char authority, String pwd, String joindate, boolean withdrawal, String role) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.contact = contact;
        this.email = email;
        this.position = position;
        this.department = department;
        this.authority = authority;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public char getAuthority() {
        return authority;
    }

    public void setAuthority(char authority) {
        this.authority = authority;
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
        return "ManagerDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birth='" + birth + '\'' +
                ", gender=" + gender +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                ", authority=" + authority +
                ", pwd='" + pwd + '\'' +
                ", joindate='" + joindate + '\'' +
                ", withdrawal=" + withdrawal +
                ", role='" + role + '\'' +
                '}';
    }
}
