package com.epam.spring.cargo_delivery.controller.dto;

import com.epam.spring.cargo_delivery.controller.dto.group.OnCreate;
import com.epam.spring.cargo_delivery.controller.dto.group.OnUpdate;
import com.epam.spring.cargo_delivery.service.model.Role;
import com.epam.spring.cargo_delivery.util.validation.unique.Unique;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

  @Null(groups = OnCreate.class, message = "{common.id.empty}")
  @NotNull(groups = OnUpdate.class, message = "{common.id.not_empty}")
  private Long id;

  @NotBlank(message = "{user.login.not_blank}")
  @Size(min = 2, message = "{user.login.size}")
  @Unique(groups = OnCreate.class, message = "{user.login.unique}")
  private String login;

  @NotBlank
  @ToString.Exclude
  @Pattern(regexp = "(?=^.{8,}$)(?=.*\\d)(?=.*[!@#$%^&*]+)(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).{8,24}$")
  private String password;

  @Null(groups = OnCreate.class, message = "{user.roleID.null}")
  @NotNull(groups = OnUpdate.class, message = "{user.roleID.not_null}")
  private Role role;

  @NotBlank(message = "{user.name.not_blank}")
  @Size(min = 2, message = "{user.name.size}")
  private String name;

  @NotBlank(message = "{user.surname.not_blank}")
  @Size(min = 2, message = "{user.surname.size}")
  private String surname;

  @NotBlank(message = "{user.email.not_blank}")
  @Email
  private String email;

  @NotBlank(message = "{user.phone.not_blank}")
  @Pattern(regexp = "^\\+(380)[0-9]{9}$", message = "{user.phone.pattern}")
  private String phone;

  @Null(groups = OnCreate.class, message = "{user.balance.null}")
  @NotNull(groups = OnUpdate.class, message = "{user.balance.not_null}")
  @PositiveOrZero(groups = OnUpdate.class, message = "{user.balance.positive_or_zero}")
  private Double balance;
}
