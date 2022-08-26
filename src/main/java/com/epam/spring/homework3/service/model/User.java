package com.epam.spring.homework3.service.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
public class User {
    private Long id;
    private String login;
    @ToString.Exclude
    private String password;
    private int roleID;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private double balance;
}
