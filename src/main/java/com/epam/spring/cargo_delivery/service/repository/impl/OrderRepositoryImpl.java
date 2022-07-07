package com.epam.spring.cargo_delivery.service.repository.impl;

import com.epam.spring.cargo_delivery.service.exception.EntityNotFoundException;
import com.epam.spring.cargo_delivery.service.model.Order;
import com.epam.spring.cargo_delivery.service.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderRepositoryImpl implements OrderRepository {

  private final List<Order> list = new ArrayList<>();
  private static long ID_COUNT = 0;

  @Override
  public Order getOrder(long id) {
    log.info("Get order for id {}", id);
    return list.stream().filter(o -> o.getId().equals(id))
        .findFirst()
        .orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public List<Order> getOrders() {
    log.info("Get all orders");
    return new ArrayList<>(list);
  }

  @Override
  public Order createOrder(Order order) {
    log.info("Create order {}", order);
    order.setId(++ID_COUNT);
    list.add(order);
    return order;
  }

  @Override
  public Order updateOrder(long id, Order order) {
    log.info("Update order for id {}", order);
    boolean isDeleted = list.removeIf(o -> o.getId().equals(id));
    if (isDeleted) {
      list.add(order);
    } else {
      throw new EntityNotFoundException();
    }
    return order;
  }

  @Override
  public void deleteOrder(long id) {
    log.info("Delete order for id {}", id);
    list.removeIf(o -> o.getId().equals(id));
  }
}
