package com.epam.spring.homework3.controller.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDTO {

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
