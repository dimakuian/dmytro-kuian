package com.epam.spring.homework3.controller.assembler;

import com.epam.spring.homework3.controller.UserController;
import com.epam.spring.homework3.controller.dto.UserDTO;
import com.epam.spring.homework3.controller.model.UserModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<UserDTO, UserModel> {

    public static final String GET_REL = "get_user";
    public static final String CREATE_REL = "create_user";
    public static final String UPDATE_REL = "update_user";
    public static final String DELETE_REL = "delete_user";

    public UserAssembler(Class<?> controllerClass, Class<UserModel> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    public UserModel toModel(UserDTO entity) {
        UserModel userModel = new UserModel(entity);
        Link get = linkTo(methodOn(UserController.class).getUser(entity.getId())).withRel(GET_REL);
        Link create = linkTo(methodOn(UserController.class).createUser(entity)).withRel(CREATE_REL);
        Link update = linkTo(methodOn(UserController.class).updateUser(entity.getId(), entity)).withRel(UPDATE_REL);
        Link delete = linkTo(methodOn(UserController.class).deleteUser(entity.getId())).withRel(DELETE_REL);

        userModel.add(get, create, update, delete);
        return userModel;
    }
}
