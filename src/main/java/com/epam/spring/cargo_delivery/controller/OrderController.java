package com.epam.spring.cargo_delivery.controller;

import com.epam.spring.cargo_delivery.controller.assembler.OrderAssembler;
import com.epam.spring.cargo_delivery.controller.dto.OrderDTO;
import com.epam.spring.cargo_delivery.controller.model.OrderModel;
import com.epam.spring.cargo_delivery.service.OrderService;
import com.epam.spring.cargo_delivery.service.api.OrderApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController implements OrderApi {

  private final OrderService orderService;
  private final OrderAssembler orderAssembler;

  @Override
  public Page<OrderDTO> getAllOrders(Pageable pageable) {
    log.info("Get all orders with pageable {}", pageable);
    return orderService.getOrders(pageable);
  }

  @Override
  public OrderModel getOrder(long id) {
    log.info("Get order with id {}", id);
    OrderDTO outOrderDto = orderService.getOrder(id);
    return orderAssembler.toModel(outOrderDto);
  }

  @Override
  public OrderModel createOrder(OrderDTO orderDTO) {
    log.info("Create order : {}", orderDTO);
    OrderDTO outOrderDto = orderService.createOrder(orderDTO);
    return orderAssembler.toModel(outOrderDto);
  }

  @Override
  public OrderModel updateOrder(long id, OrderDTO orderDTO) {
    log.info("Update order by id {}", id);
    log.trace("Request body orderDTO {}", orderDTO);
    OrderDTO outOrderDto = orderService.updateOrder(id, orderDTO);
    return orderAssembler.toModel(outOrderDto);
  }

  @Override
  public ResponseEntity<Void> deleteOrder(long id) {
    log.info("Delete order by id {}", id);
    orderService.deleteOrder(id);
    return ResponseEntity.noContent().build();
  }
}
