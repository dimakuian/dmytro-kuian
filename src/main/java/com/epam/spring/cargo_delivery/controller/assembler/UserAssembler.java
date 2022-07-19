package com.epam.spring.cargo_delivery.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.epam.spring.cargo_delivery.controller.UserController;
import com.epam.spring.cargo_delivery.controller.dto.UserDTO;
import com.epam.spring.cargo_delivery.controller.model.UserModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<UserDTO, UserModel> {

  private static final String GET_REL = "get_user";
  private static final String CREATE_REL = "create_user";
  private static final String UPDATE_REL = "update_user";
  private static final String DELETE_REL = "delete_user";

  public UserAssembler() {
    super(UserController.class, UserModel.class);
  }

  @Override
  public UserModel toModel(UserDTO entity) {
    UserModel userModel = new UserModel(entity);

    Link get = linkTo(methodOn(UserController.class).getUser(entity.getId())).withRel(GET_REL);
    Link create = linkTo(methodOn(UserController.class).createUser(entity)).withRel(CREATE_REL);
    Link update = linkTo(methodOn(UserController.class).updateUser(entity.getId(), entity))
        .withRel(UPDATE_REL);
    Link delete = linkTo(methodOn(UserController.class).deleteUser(entity.getId()))
        .withRel(DELETE_REL);

    userModel.add(get, create, update, delete);
    return userModel;
  }
}
