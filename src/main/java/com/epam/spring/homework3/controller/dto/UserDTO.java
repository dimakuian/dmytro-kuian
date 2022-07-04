package com.epam.spring.homework3.controller.dto;

import com.epam.spring.homework3.controller.dto.group.OnCreate;
import com.epam.spring.homework3.controller.dto.group.OnUpdate;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserDTO {

    @Null(groups = OnCreate.class, message = "Id should be empty!")
    @NotNull(groups = OnUpdate.class, message = "Id should not be empty!")
    private Long id;

    @NotBlank(message = "Login should not be empty!")
    @Size(min = 2)
    private String login;

    @Null(groups = OnCreate.class, message = "Role id should be empty!")
    @NotNull(groups = OnUpdate.class, message = "Role id should not be empty!")
    private Integer roleID;

    @NotBlank(message = "Name should not be empty!")
    @Size(min = 2)
    private String name;

    @NotBlank(message = "Surname should not be empty!")
    @Size(min = 2)
    private String surname;

    @NotBlank(message = "Email should not be empty!")
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\+(380)[0-9]{9}$",message = "Phone is not valid, example : +380671234567")
    private String phone;

    @Null(groups = OnCreate.class, message = "Balance should be empty!")
    @NotNull(groups = OnUpdate.class, message = "Balance should not be empty!")
    @Positive(groups = OnUpdate.class)
    private Double balance;
}
