package com.epam.spring.homework3.util;

import com.epam.spring.homework3.service.model.enums.InvoiceStatus;
import com.epam.spring.homework3.service.repository.InvoiceRepository;
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
    Map<String, Long> invoiceDetails = Arrays.stream(InvoiceStatus.values())
        .collect(Collectors.toMap(InvoiceStatus::getName, invoiceRepository::countInvoiceByStatus));

    builder.withDetail("invoices", invoiceDetails);
  }

}
