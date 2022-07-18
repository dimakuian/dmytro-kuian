package com.epam.spring.cargo_delivery.controller.model;

import com.epam.spring.cargo_delivery.controller.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class UserModel extends RepresentationModel<UserModel> {

  @JsonUnwrapped
  private UserDTO userDTO;
}