package com.epam.spring.cargo_delivery.service.impl;

import com.epam.spring.cargo_delivery.controller.dto.OrderDTO;
import com.epam.spring.cargo_delivery.controller.dto.ShippingStatusDTO;
import com.epam.spring.cargo_delivery.service.OrderService;
import com.epam.spring.cargo_delivery.service.exception.EntityNotFoundException;
import com.epam.spring.cargo_delivery.service.mapper.OrderMapper;
import com.epam.spring.cargo_delivery.service.model.Order;
import com.epam.spring.cargo_delivery.service.repository.LocalityRepository;
import com.epam.spring.cargo_delivery.service.repository.OrderRepository;
import com.epam.spring.cargo_delivery.service.repository.ShippingStatusRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final ShippingStatusRepository shippingStatusRepository;
  private final LocalityRepository localityRepository;

  @Override
  public OrderDTO getOrder(long id) {
    log.info("Start getting delivery order by id {}", id);
    Order order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    log.info("Finish getting delivery order by id {}", id);
    return OrderMapper.INSTANCE.mapOrderDto(order);
  }

  @Override
  public Page<OrderDTO> getOrders(Pageable pageable) {
    log.info("Getting all orders");
    return orderRepository.findAll(pageable).map(OrderMapper.INSTANCE::mapOrderDto);
  }

  @Transactional
  @Override
  public OrderDTO createOrder(OrderDTO orderDTO) {
    log.info("Start creating order {}", orderDTO);
    orderDTO.setCreationTime(Timestamp.valueOf(LocalDateTime.now()));

    orderDTO.setShippingStatus(
        shippingStatusRepository
            .findByName(ShippingStatusDTO.CREATED.name().toLowerCase())
            .orElseThrow(EntityNotFoundException::new));

    Long shippingID = orderDTO.getShippingLocality().getId();
    Long deliveryID = orderDTO.getDeliveryLocality().getId();
    orderDTO.setDistance(
        localityRepository.calcDistanceBetweenTwoLocality(shippingID, deliveryID)
            .orElseThrow(EntityNotFoundException::new)
    );

    Order order = OrderMapper.INSTANCE.mapOrder(orderDTO);
    order = orderRepository.save(order);

    log.info("Finish creating order {}", order);
    return OrderMapper.INSTANCE.mapOrderDto(order);
  }

  @Override
  public OrderDTO updateOrder(long id, OrderDTO orderDTO) {
    log.info("Start updating order for id {}", id);

    if (!orderRepository.existsById(id)) {
      throw new EntityNotFoundException();
    }

    Order order = OrderMapper.INSTANCE.mapOrder(orderDTO);
    order = orderRepository.save(order);
    log.info("Finish updating order for id {}", id);
    return OrderMapper.INSTANCE.mapOrderDto(order);
  }

  @Override
  public void deleteOrder(long id) {
    log.info("Start deleting order for id {}", id);
    if (!orderRepository.existsById(id)) {
      throw new EntityNotFoundException();
    }
    orderRepository.deleteById(id);
    log.info("Deleted order by id {}", id);
  }
}
