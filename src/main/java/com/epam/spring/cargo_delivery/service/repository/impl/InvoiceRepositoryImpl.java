package com.epam.spring.cargo_delivery.service.repository.impl;

import com.epam.spring.cargo_delivery.service.exception.EntityNotFoundException;
import com.epam.spring.cargo_delivery.service.model.Invoice;
import com.epam.spring.cargo_delivery.service.model.enums.InvoiceStatus;
import com.epam.spring.cargo_delivery.service.repository.InvoiceRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InvoiceRepositoryImpl implements InvoiceRepository {

  private final List<Invoice> list = new ArrayList<>();
  private static long ID_COUNT = 0;

  @Override
  public Invoice getInvoice(long id) {
    log.info("Get invoice for id {}", id);
    return list.stream().filter(invoice -> invoice.getId().equals(id))
        .findFirst()
        .orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public List<Invoice> getInvoices() {
    log.info("Get all invoices");
    return new ArrayList<>(list);
  }

  @Override
  public Invoice createInvoice(Invoice invoice) {
    log.info("Create invoice {}", invoice);
    invoice.setId(++ID_COUNT);
    list.add(invoice);
    return invoice;
  }

  @Override
  public Invoice updateInvoice(long id, Invoice invoice) {
    log.info("Update invoice with id {}", id);
    boolean isDeleted = list.removeIf(i -> i.getId().equals(id));
    if (isDeleted) {
      list.add(invoice);
    } else {
      throw new EntityNotFoundException();
    }
    return invoice;
  }

  @Override
  public void deleteInvoice(long id) {
    log.info("Delete invoice with id {}", id);
    list.removeIf(i -> i.getId().equals(id));
  }

  @Override
  public long countInvoiceByStatus(InvoiceStatus invoiceStatus) {
    log.info("Count invoice by status {}", invoiceStatus.getName());
    return list.stream()
        .filter(invoice -> invoice.getInvoiceStatusID() == invoiceStatus.ordinal())
        .count();
  }
}
