package com.epam.spring.cargo_delivery.service.model.enums;

import com.epam.spring.cargo_delivery.service.model.User;

public enum Role {
  ADMIN, CLIENT;

  public static Role getRole(User user) {
    int roleId = user.getRoleID();
    return Role.values()[roleId];

  }

  public String getName() {
    return name().toLowerCase();
  }

}
