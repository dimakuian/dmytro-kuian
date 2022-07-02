package com.epam.spring.homework3.service.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class Locality {
    private Long id;
    private String city;
    private String street;
    private String streetNumber;
    private double latitude;
    private double longitude;

}
