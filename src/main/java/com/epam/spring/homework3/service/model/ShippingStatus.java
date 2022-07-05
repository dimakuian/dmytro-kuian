package com.epam.spring.homework3.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShippingStatus {

    private Long id;
    private String name;

}
