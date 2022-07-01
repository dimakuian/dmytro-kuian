package com.epam.spring.homework3.controller.dto;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderDTO {

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
