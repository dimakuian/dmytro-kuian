package com.epam.spring.cargo_delivery.service.test;

import com.epam.spring.cargo_delivery.controller.dto.RoleDTO;
import com.epam.spring.cargo_delivery.controller.dto.UserDTO;
import com.epam.spring.cargo_delivery.service.model.Role;
import com.epam.spring.cargo_delivery.service.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUserDataUtil {

  public static final Long ID = 1L;
  public static final String LOGIN = "test_login";
  public static final String PASSWORD = "test_password";
  public static final Role ROLE = createClientRole();
  public static final String NAME = "test_name";
  public static final String SURNAME = "test_surname";
  public static final String EMAIL = "test@mail.com";
  public static final String PHONE = "+380671234567";
  public static final double BALANCE = 0.0;
  public static final String ROLE_NAME = "client";

  public static Role createClientRole() {
    return Role.builder().id(1L).name(RoleDTO.CLIENT.name().toLowerCase()).build();
  }

  public static User createUser() {
    return User.builder()
        .id(ID)
        .login(LOGIN)
        .password(PASSWORD)
        .role(ROLE)
        .name(NAME)
        .surname(SURNAME)
        .email(EMAIL)
        .phone(PHONE)
        .balance(BALANCE)
        .build();
  }

  public static UserDTO createUserDTO() {
    return UserDTO.builder()
        .id(ID)
        .login(LOGIN)
        .password(PASSWORD)
        .role(ROLE)
        .name(NAME)
        .surname(SURNAME)
        .email(EMAIL)
        .phone(PHONE)
        .balance(BALANCE)
        .build();
  }
}
