package com.epam.spring.homework3.controller.dto;

import lombok.Data;

@Data
public class LocalityDTO {
    private String city;
    private String street;
    private String streetNumber;
    private double latitude;
    private double longitude;
}
