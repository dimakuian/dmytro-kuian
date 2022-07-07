package com.epam.spring.cargo_delivery.service.model;


import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {

  private Long id;
  private long shippingAddressID;
  private long deliveryAddressID;
  private Timestamp creationTime;
  private long clientID;
  private String consignee;
  private String description;
  private double distance;
  private double length;
  private double height;
  private double width;
  private double weight;
  private double volume;
  private double fare;
  private long statusID;
  private Timestamp deliveryDate;
}
