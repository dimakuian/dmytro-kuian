package com.epam.spring.cargo_delivery.service.impl;

import static com.epam.spring.cargo_delivery.service.test.TestOrderDataUtil.CONSIGNEE;
import static com.epam.spring.cargo_delivery.service.test.TestOrderDataUtil.CREATION_TIME;
import static com.epam.spring.cargo_delivery.service.test.TestOrderDataUtil.DELIVERY_LOCALITY;
import static com.epam.spring.cargo_delivery.service.test.TestOrderDataUtil.DESCRIPTION;
import static com.epam.spring.cargo_delivery.service.test.TestOrderDataUtil.DISTANCE;
import static com.epam.spring.cargo_delivery.service.test.TestOrderDataUtil.FARE;
import static com.epam.spring.cargo_delivery.service.test.TestOrderDataUtil.HEIGHT;
import static com.epam.spring.cargo_delivery.service.test.TestOrderDataUtil.LENGTH;
import static com.epam.spring.cargo_delivery.service.test.TestOrderDataUtil.SHIPPING_LOCALITY;
import static com.epam.spring.cargo_delivery.service.test.TestOrderDataUtil.SHIPPING_STATUS;
import static com.epam.spring.cargo_delivery.service.test.TestOrderDataUtil.SHIPPING_STATUS_NAME;
import static com.epam.spring.cargo_delivery.service.test.TestOrderDataUtil.USER;
import static com.epam.spring.cargo_delivery.service.test.TestOrderDataUtil.VOLUME;
import static com.epam.spring.cargo_delivery.service.test.TestOrderDataUtil.WEIGHT;
import static com.epam.spring.cargo_delivery.service.test.TestOrderDataUtil.WIDTH;
import static com.epam.spring.cargo_delivery.service.test.TestUserDataUtil.ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.spring.cargo_delivery.controller.dto.OrderDTO;
import com.epam.spring.cargo_delivery.service.exception.EntityNotFoundException;
import com.epam.spring.cargo_delivery.service.mapper.OrderMapper;
import com.epam.spring.cargo_delivery.service.model.Order;
import com.epam.spring.cargo_delivery.service.repository.LocalityRepository;
import com.epam.spring.cargo_delivery.service.repository.OrderRepository;
import com.epam.spring.cargo_delivery.service.repository.ShippingStatusRepository;
import com.epam.spring.cargo_delivery.service.test.TestOrderDataUtil;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

  @InjectMocks
  private OrderServiceImpl orderService;

  @Mock
  private OrderRepository orderRepository;

  @Mock
  private ShippingStatusRepository statusRepository;

  @Mock
  private LocalityRepository localityRepository;

  @Test
  void getOrderTest() {
    Order testOrder = TestOrderDataUtil.createOrder();

    when(orderRepository.findById(ID)).thenReturn(Optional.of(testOrder));

    OrderDTO returnOrderDTO = orderService.getOrder(ID);

    assertThat(returnOrderDTO,
        allOf(
            hasProperty("id", equalTo(ID)),
            hasProperty("shippingLocality", equalTo(SHIPPING_LOCALITY)),
            hasProperty("deliveryLocality", equalTo(DELIVERY_LOCALITY)),
            hasProperty("creationTime", equalTo(CREATION_TIME)),
            hasProperty("user", equalTo(USER)),
            hasProperty("consignee", equalTo(CONSIGNEE)),
            hasProperty("description", equalTo(DESCRIPTION)),
            hasProperty("distance", equalTo(DISTANCE)),
            hasProperty("length", equalTo(LENGTH)),
            hasProperty("height", equalTo(HEIGHT)),
            hasProperty("width", equalTo(WIDTH)),
            hasProperty("weight", equalTo(WEIGHT)),
            hasProperty("volume", equalTo(VOLUME)),
            hasProperty("shippingStatus", equalTo(SHIPPING_STATUS)),
            hasProperty("fare", equalTo(FARE))));

    verify(orderRepository, times(1)).findById(ID);
  }

  @Test
  void getOrderWhenNotExistTest() {
    when(orderRepository.findById(ID)).thenReturn(Optional.empty());
    assertThrows(EntityNotFoundException.class, () -> orderService.getOrder(ID));

    verify(orderRepository, times(1)).findById(ID);
  }

  @Test
  void getOrdersTest() {
    OrderDTO orderDTO1 = TestOrderDataUtil.createOrderDTO();
    OrderDTO orderDTO2 = TestOrderDataUtil.createOrderDTO();
    OrderDTO orderDTO3 = TestOrderDataUtil.createOrderDTO();

    orderDTO2.setId(2L);

    orderDTO3.setId(3L);

    List<Order> orders = Stream.of(orderDTO1, orderDTO2, orderDTO3)
        .map(OrderMapper.INSTANCE::mapOrder)
        .collect(Collectors.toList());

    Pageable pageable = Pageable.unpaged();

    when(orderRepository.findAll(pageable))
        .thenReturn(new PageImpl<>(orders, pageable, orders.size()));

    Page<OrderDTO> returnOrderDTOS = orderService.getOrders(pageable);

    assertThat(returnOrderDTOS.getContent(), hasItems(orderDTO1, orderDTO2, orderDTO3));
    verify(orderRepository, times(1)).findAll(pageable);
  }

  @Test
  void createOrderTest() {
    Order testOrder = TestOrderDataUtil.createOrder();
    OrderDTO testOrderDTO = TestOrderDataUtil.createOrderDTO();

    when(orderRepository.save(any())).thenReturn(testOrder);
    when(statusRepository.findByName(SHIPPING_STATUS_NAME))
        .thenReturn(Optional.of(SHIPPING_STATUS));
    when(localityRepository.calcDistanceBetweenTwoLocality(testOrder.getShippingLocality().getId(),
        testOrder.getDeliveryLocality().getId())).thenReturn(Optional.of(DISTANCE));

    OrderDTO returnOrderDTO = orderService.createOrder(testOrderDTO);

    assertThat(
        returnOrderDTO,
        allOf(
            hasProperty("id", equalTo(testOrder.getId())),
            hasProperty("shippingLocality", equalTo(testOrder.getShippingLocality())),
            hasProperty("deliveryLocality", equalTo(testOrder.getDeliveryLocality())),
            hasProperty("creationTime", equalTo(testOrder.getCreationTime())),
            hasProperty("user", equalTo(testOrder.getUser())),
            hasProperty("consignee", equalTo(testOrder.getConsignee())),
            hasProperty("description", equalTo(testOrder.getDescription())),
            hasProperty("distance", equalTo(testOrder.getDistance())),
            hasProperty("length", equalTo(testOrder.getLength())),
            hasProperty("height", equalTo(testOrder.getHeight())),
            hasProperty("width", equalTo(testOrder.getWidth())),
            hasProperty("weight", equalTo(testOrder.getWeight())),
            hasProperty("volume", equalTo(testOrder.getVolume())),
            hasProperty("shippingStatus", equalTo(testOrder.getShippingStatus())),
            hasProperty("fare", equalTo(testOrder.getFare()))));

    verify(orderRepository, times(1)).save(any());
  }


  @Test
  void updateOrderTest() {
    Order testOrder = TestOrderDataUtil.createOrder();
    OrderDTO testOrderDTO = TestOrderDataUtil.createOrderDTO();

    when(orderRepository.existsById(ID)).thenReturn(true);
    when(orderRepository.save(any())).thenReturn(testOrder);

    OrderDTO returnOrderDTO = orderService.updateOrder(ID, testOrderDTO);

    assertThat(
        returnOrderDTO,
        allOf(
            hasProperty("id", equalTo(testOrder.getId())),
            hasProperty("shippingLocality", equalTo(testOrder.getShippingLocality())),
            hasProperty("deliveryLocality", equalTo(testOrder.getDeliveryLocality())),
            hasProperty("creationTime", equalTo(testOrder.getCreationTime())),
            hasProperty("user", equalTo(testOrder.getUser())),
            hasProperty("consignee", equalTo(testOrder.getConsignee())),
            hasProperty("description", equalTo(testOrder.getDescription())),
            hasProperty("distance", equalTo(testOrder.getDistance())),
            hasProperty("length", equalTo(testOrder.getLength())),
            hasProperty("height", equalTo(testOrder.getHeight())),
            hasProperty("width", equalTo(testOrder.getWidth())),
            hasProperty("weight", equalTo(testOrder.getWeight())),
            hasProperty("volume", equalTo(testOrder.getVolume())),
            hasProperty("shippingStatus", equalTo(testOrder.getShippingStatus())),
            hasProperty("fare", equalTo(testOrder.getFare()))));

    verify(orderRepository, times(1)).save(any());
    verify(orderRepository, times(1)).existsById(ID);
  }

  @Test
  void updateWhenOrderNotExistTest() {
    when(orderRepository.existsById(ID)).thenReturn(false);

    OrderDTO testOrderDTO = TestOrderDataUtil.createOrderDTO();

    assertThrows(EntityNotFoundException.class, () -> orderService.updateOrder(ID, testOrderDTO));
    verify(orderRepository, times(1)).existsById(ID);
  }

  @Test
  void deleteOrderTest() {
    when(orderRepository.existsById(ID)).thenReturn(true);

    orderService.deleteOrder(ID);

    verify(orderRepository, times(1)).existsById(ID);
    verify(orderRepository, times(1)).deleteById(ID);
  }

  @Test
  void deleteWhenOrderNotExistTest() {
    assertThrows(EntityNotFoundException.class, () -> orderService.deleteOrder(ID));
    verify(orderRepository, times(1)).existsById(ID);
  }
}
