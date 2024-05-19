package com.ohgiraffers.dosirak.user.review.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
@ToString
public class UserDTO {

    private String id;
    private String name;
    private LocalDate birth;
    private String gender;
    private String phone;
    private String email;
    private String address1;
    private String address2;
    private String address3;
    private String agree;
    private String pwd;
    private LocalDate joinDate;
    private int withDrawal;
    private String role;
}
