package com.epam.spring.cargo_delivery.service.test.util;

import com.epam.spring.cargo_delivery.controller.dto.OrderDTO;
import com.epam.spring.cargo_delivery.controller.dto.ShippingStatusDTO;
import com.epam.spring.cargo_delivery.service.model.Locality;
import com.epam.spring.cargo_delivery.service.model.Order;
import com.epam.spring.cargo_delivery.service.model.ShippingStatus;
import com.epam.spring.cargo_delivery.service.model.User;
import java.sql.Timestamp;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestOrderDataUtil {

  public static final Long ID = 1L;
  public static final Locality SHIPPING_LOCALITY = createShippingLocality();
  public static final Locality DELIVERY_LOCALITY = createDeliveryLocality();
  public static final Timestamp CREATION_TIME = Timestamp.valueOf("2022-07-19 10:24:23");
  public static final User USER = TestUserDataUtil.createUser();
  public static final String CONSIGNEE = "test_consignee";
  public static final String DESCRIPTION = "test_description";
  public static final double DISTANCE = 570.0;
  public static final double LENGTH = 0.1;
  public static final double HEIGHT = 0.2;
  public static final double WIDTH = 0.2;
  public static final double WEIGHT = 2.0;
  public static final double VOLUME = 0.4;
  public static final double FARE = 65.6;
  public static final ShippingStatus SHIPPING_STATUS = createShippingStatus();
  public static final String SHIPPING_STATUS_NAME = "created";
  public static final Timestamp DELIVERY_DATE = Timestamp.valueOf("2022-07-22 10:00:00");


  public static Order createOrder() {
    return Order.builder()
        .id(ID)
        .shippingLocality(SHIPPING_LOCALITY)
        .deliveryLocality(DELIVERY_LOCALITY)
        .creationTime(CREATION_TIME)
        .user(USER)
        .consignee(CONSIGNEE)
        .description(DESCRIPTION)
        .distance(DISTANCE)
        .length(LENGTH)
        .height(HEIGHT)
        .width(WIDTH)
        .weight(WEIGHT)
        .volume(VOLUME)
        .fare(FARE)
        .shippingStatus(SHIPPING_STATUS)
        .build();
  }

  public static OrderDTO createOrderDTO() {
    return OrderDTO.builder()
        .id(ID)
        .shippingLocality(SHIPPING_LOCALITY)
        .deliveryLocality(DELIVERY_LOCALITY)
        .creationTime(CREATION_TIME)
        .user(USER)
        .consignee(CONSIGNEE)
        .description(DESCRIPTION)
        .distance(DISTANCE)
        .length(LENGTH)
        .height(HEIGHT)
        .width(WIDTH)
        .weight(WEIGHT)
        .volume(VOLUME)
        .fare(FARE)
        .shippingStatus(SHIPPING_STATUS)
        .build();
  }

  private static Locality createShippingLocality() {
    return Locality.builder()
        .id(1L)
        .name("Lviv department 1")
        .latitude(49.85580301226521)
        .longitude(24.019571021514157)
        .build();
  }

  private static Locality createDeliveryLocality() {
    return Locality.builder()
        .id(2L)
        .name("Kyiv department 1")
        .latitude(50.430152159229465)
        .longitude(30.400358449390378)
        .build();
  }

  private static ShippingStatus createShippingStatus() {
    return ShippingStatus.builder()
        .id(1L)
        .name(ShippingStatusDTO.CREATED.name().toLowerCase())
        .build();
  }
}
