package com.epam.spring.homework3.service.model;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Order {

    private Long id;
    private long shippingAddressID;
    private long deliveryAddressID;
    private LocalDate creationTime;
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
    private LocalDate deliveryDate;

}
