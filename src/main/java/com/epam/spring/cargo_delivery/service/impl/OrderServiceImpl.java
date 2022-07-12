package com.epam.spring.cargo_delivery.service.impl;

import com.epam.spring.cargo_delivery.controller.dto.DeliveryOrderDTO;
import com.epam.spring.cargo_delivery.controller.dto.ShippingStatusDTO;
import com.epam.spring.cargo_delivery.service.OrderService;
import com.epam.spring.cargo_delivery.service.exception.EntityNotFoundException;
import com.epam.spring.cargo_delivery.service.mapper.DeliveryOrderMapper;
import com.epam.spring.cargo_delivery.service.model.DeliveryOrder;
import com.epam.spring.cargo_delivery.service.repository.OrderRepository;
import com.epam.spring.cargo_delivery.service.repository.ShippingStatusRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final ShippingStatusRepository shippingStatusRepository;

  @Override
  public DeliveryOrderDTO getOrder(long id) {
    log.info("Start getting delivery order by id {}", id);
    DeliveryOrder order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    log.info("Finish getting delivery order by id {}", id);
    return DeliveryOrderMapper.INSTANCE.mapOrderDto(order);
  }

  @Override
  public List<DeliveryOrderDTO> getOrders() {
    log.info("Getting all orders");
    List<DeliveryOrder> orders = orderRepository.findAll();
    return DeliveryOrderMapper.INSTANCE.mapOrderDtos(orders);
  }

  @Override
  public DeliveryOrderDTO createOrder(DeliveryOrderDTO deliveryOrderDTO) {
    log.info("Start creating order {}", deliveryOrderDTO);
    deliveryOrderDTO.setCreationTime(Timestamp.valueOf(LocalDateTime.now()));

    deliveryOrderDTO.setShippingStatus(
        shippingStatusRepository
            .findByName(ShippingStatusDTO.CREATED.name())
            .orElseThrow(RuntimeException::new));

    DeliveryOrder order = DeliveryOrderMapper.INSTANCE.mapOrder(deliveryOrderDTO);
    order = orderRepository.save(order);

    log.info("Finish creating order {}", deliveryOrderDTO);
    return DeliveryOrderMapper.INSTANCE.mapOrderDto(order);
  }

  @Override
  public DeliveryOrderDTO updateOrder(long id, DeliveryOrderDTO deliveryOrderDTO) {
    log.info("Start updating order for id {}", id);

    if (!orderRepository.existsById(id)) {
      throw new EntityNotFoundException();
    }

    DeliveryOrder order = DeliveryOrderMapper.INSTANCE.mapOrder(deliveryOrderDTO);
    order = orderRepository.save(order);
    log.info("Finish updating order for id {}", id);
    return DeliveryOrderMapper.INSTANCE.mapOrderDto(order);
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
