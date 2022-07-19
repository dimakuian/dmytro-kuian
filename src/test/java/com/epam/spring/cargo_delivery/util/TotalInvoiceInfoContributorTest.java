package com.epam.spring.cargo_delivery.util;

import static com.epam.spring.cargo_delivery.controller.dto.InvoiceStatusDTO.CREATED;
import static com.epam.spring.cargo_delivery.controller.dto.InvoiceStatusDTO.DECLINED;
import static com.epam.spring.cargo_delivery.controller.dto.InvoiceStatusDTO.PAID;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.spring.cargo_delivery.service.repository.InvoiceRepository;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.actuate.info.Info.Builder;

@ExtendWith(MockitoExtension.class)
class TotalInvoiceInfoContributorTest {

  @InjectMocks
  private TotalInvoiceInfoContributor contributor;

  @Mock
  private InvoiceRepository invoiceRepository;

  @Mock
  private Builder builder;

  @Test
  void contributeTest() {
    Map<String, Long> invoiceDetails = new HashMap<>();

    invoiceDetails.put(DECLINED.name().toLowerCase(), 5L);
    invoiceDetails.put(CREATED.name().toLowerCase(), 2L);
    invoiceDetails.put(PAID.name().toLowerCase(), 10L);

    when(invoiceRepository.countAllByInvoiceStatusName(DECLINED.name()))
        .thenReturn(5L);
    when(invoiceRepository.countAllByInvoiceStatusName(PAID.name()))
        .thenReturn(10L);
    when(invoiceRepository.countAllByInvoiceStatusName(CREATED.name()))
        .thenReturn(2L);
    when(builder.withDetail("invoices", invoiceDetails)).thenReturn(builder);

    contributor.contribute(builder);

    verify(builder, times(1)).withDetail("invoices", invoiceDetails);
  }
}
