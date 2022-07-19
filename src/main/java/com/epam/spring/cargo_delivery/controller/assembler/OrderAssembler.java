package com.epam.spring.cargo_delivery.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.epam.spring.cargo_delivery.controller.OrderController;
import com.epam.spring.cargo_delivery.controller.dto.OrderDTO;
import com.epam.spring.cargo_delivery.controller.model.OrderModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class OrderAssembler extends
    RepresentationModelAssemblerSupport<OrderDTO, OrderModel> {

  private static final String GET_REL = "get_order";
  private static final String CREATE_REL = "create_order";
  private static final String UPDATE_REL = "update_order";
  private static final String DELETE_REL = "delete_order";

  public OrderAssembler() {
    super(OrderController.class, OrderModel.class);
  }

  @Override
  public OrderModel toModel(OrderDTO entity) {
    OrderModel orderModel = new OrderModel(entity);

    Link get = linkTo(methodOn(OrderController.class).getOrder(entity.getId())).withRel(GET_REL);
    Link create = linkTo(methodOn(OrderController.class).createOrder(entity)).withRel(CREATE_REL);
    Link update = linkTo(methodOn(OrderController.class).updateOrder(entity.getId(), entity))
        .withRel(UPDATE_REL);
    Link delete = linkTo(methodOn(OrderController.class).deleteOrder(entity.getId()))
        .withRel(DELETE_REL);

    orderModel.add(get, create, update, delete);
    return orderModel;
  }
}
