package com.epam.spring.homework3.service.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ShippingStatus {

    private Long id;
    private String name;

}
