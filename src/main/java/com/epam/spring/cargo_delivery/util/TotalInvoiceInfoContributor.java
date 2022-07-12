package com.epam.spring.cargo_delivery.util;

import com.epam.spring.cargo_delivery.controller.dto.InvoiceStatusDTO;
import com.epam.spring.cargo_delivery.service.repository.InvoiceRepository;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TotalInvoiceInfoContributor implements InfoContributor {

  private final InvoiceRepository invoiceRepository;

  @Override
  public void contribute(Builder builder) {
    Map<String, Long> invoiceDetails = Arrays.stream(InvoiceStatusDTO.values())
        .map(s -> s.name().toLowerCase())
        .collect(Collectors.toMap(String::toLowerCase,
            invoiceRepository::countAllByInvoiceStatusName));

    builder.withDetail("invoices", invoiceDetails);
  }
}
