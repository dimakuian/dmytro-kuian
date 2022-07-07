package com.epam.spring.cargo_delivery.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Locality {

  private Long id;
  private String city;
  private String street;
  private String streetNumber;
  private double latitude;
  private double longitude;
}
