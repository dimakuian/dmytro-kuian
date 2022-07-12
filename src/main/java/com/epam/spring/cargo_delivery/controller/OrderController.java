package com.epam.spring.cargo_delivery.controller;

import com.epam.spring.cargo_delivery.controller.assembler.OrderAssembler;
import com.epam.spring.cargo_delivery.controller.dto.DeliveryOrderDTO;
import com.epam.spring.cargo_delivery.controller.model.OrderModel;
import com.epam.spring.cargo_delivery.service.OrderService;
import com.epam.spring.cargo_delivery.service.api.OrderApi;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi {

  private final OrderService orderService;
  private final OrderAssembler orderAssembler;

  @Override
  public List<DeliveryOrderDTO> getAllOrders() {
    return orderService.getOrders();
  }

  @Override
  public OrderModel getOrder(long id) {
    DeliveryOrderDTO outDeliveryOrderDto = orderService.getOrder(id);
    return orderAssembler.toModel(outDeliveryOrderDto);
  }

  @Override
  public OrderModel createOrder(DeliveryOrderDTO deliveryOrderDTO) {
    DeliveryOrderDTO outDeliveryOrderDto = orderService.createOrder(deliveryOrderDTO);
    return orderAssembler.toModel(outDeliveryOrderDto);
  }

  @Override
  public OrderModel updateOrder(long id, DeliveryOrderDTO deliveryOrderDTO) {
    log.info("Update order by id {}", id);
    log.trace("Request body orderDTO {}", deliveryOrderDTO);
    DeliveryOrderDTO outDeliveryOrderDto = orderService.updateOrder(id, deliveryOrderDTO);
    return orderAssembler.toModel(outDeliveryOrderDto);
  }

  @Override
  public ResponseEntity<Void> deleteOrder(long id) {
    log.info("Delete order by id {}", id);
    orderService.deleteOrder(id);
    return ResponseEntity.noContent().build();
  }
}
