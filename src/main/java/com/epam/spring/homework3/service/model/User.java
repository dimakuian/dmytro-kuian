package com.epam.spring.homework3.service.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class User {

    private long id;
    private String login;
    private String password;
    private int roleID;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private double balance;
}
