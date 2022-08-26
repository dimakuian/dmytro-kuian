package com.epam.spring.homework3.controller.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String login;
    private int roleID;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private double balance;
}
