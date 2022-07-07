package com.epam.spring.cargo_delivery.service.impl;

import com.epam.spring.cargo_delivery.controller.dto.OrderDTO;
import com.epam.spring.cargo_delivery.service.OrderService;
import com.epam.spring.cargo_delivery.service.mapper.OrderMapper;
import com.epam.spring.cargo_delivery.service.model.Order;
import com.epam.spring.cargo_delivery.service.repository.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  @Override
  public OrderDTO getOrder(long id) {
    log.info("Get order by id {}", id);
    Order order = orderRepository.getOrder(id);
    return OrderMapper.INSTANCE.mapOrderDto(order);
  }

  @Override
  public List<OrderDTO> getOrders() {
    log.info("Get all orders");
    List<Order> orders = orderRepository.getOrders();
    return OrderMapper.INSTANCE.mapOrderDtos(orders);
  }

  @Override
  public OrderDTO createOrder(OrderDTO orderDTO) {
    log.info("Create order {}", orderDTO);
    Order order = OrderMapper.INSTANCE.mapOrder(orderDTO);
    order = orderRepository.createOrder(order);
    return OrderMapper.INSTANCE.mapOrderDto(order);
  }

  @Override
  public OrderDTO updateOrder(long id, OrderDTO orderDTO) {
    log.info("Update order for id {}", id);
    Order order = OrderMapper.INSTANCE.mapOrder(orderDTO);
    order = orderRepository.updateOrder(id, order);
    return OrderMapper.INSTANCE.mapOrderDto(order);
  }

  @Override
  public void deleteOrder(long id) {
    log.info("Delete order for id {}", id);
    orderRepository.deleteOrder(id);
  }
}
