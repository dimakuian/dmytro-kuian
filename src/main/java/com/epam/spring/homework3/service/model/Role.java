package com.epam.spring.homework3.service.model;

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