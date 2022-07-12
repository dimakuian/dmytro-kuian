package com.epam.spring.cargo_delivery.service.model;


import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "delivery_order")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
  private Locality shippingLocality;

  @ManyToOne
  @JoinColumn(name = "delivery_address_id", referencedColumnName = "id")
  private Locality deliveryLocality;

  private Timestamp creationTime;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  private String consignee;

  private String description;

  private double distance;

  private double length;

  private double height;

  private double width;

  private double weight;

  private double volume;

  private double fare;

  @ManyToOne
  @JoinColumn(name = "shipping_status_id", referencedColumnName = "id")
  private ShippingStatus shippingStatus;

  private Timestamp deliveryDate;
}
