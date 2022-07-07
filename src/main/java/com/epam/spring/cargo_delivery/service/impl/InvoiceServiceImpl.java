package com.epam.spring.cargo_delivery.service.impl;

import com.epam.spring.cargo_delivery.controller.dto.InvoiceDTO;
import com.epam.spring.cargo_delivery.service.InvoiceService;
import com.epam.spring.cargo_delivery.service.mapper.InvoiceMapper;
import com.epam.spring.cargo_delivery.service.model.Invoice;
import com.epam.spring.cargo_delivery.service.repository.InvoiceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

  private final InvoiceRepository invoiceRepository;

  @Override
  public InvoiceDTO getInvoice(long id) {
    log.info("Get invoice by id {}", id);
    Invoice invoice = invoiceRepository.getInvoice(id);
    return InvoiceMapper.INSTANCE.mapInvoiceDto(invoice);
  }

  @Override
  public List<InvoiceDTO> getInvoices() {
    log.info("Get all invoices");
    List<Invoice> invoices = invoiceRepository.getInvoices();
    return InvoiceMapper.INSTANCE.mapInvoiceDtos(invoices);
  }

  @Override
  public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
    log.info("Create invoice {}", invoiceDTO);
    Invoice invoice = InvoiceMapper.INSTANCE.mapInvoice(invoiceDTO);
    invoice = invoiceRepository.createInvoice(invoice);
    return InvoiceMapper.INSTANCE.mapInvoiceDto(invoice);
  }

  @Override
  public InvoiceDTO updateInvoice(long id, InvoiceDTO invoiceDTO) {
    log.info("Update invoice for id {}", id);
    Invoice invoice = InvoiceMapper.INSTANCE.mapInvoice(invoiceDTO);
    invoice = invoiceRepository.updateInvoice(id, invoice);
    return InvoiceMapper.INSTANCE.mapInvoiceDto(invoice);
  }

  @Override
  public void deleteInvoice(long id) {
    log.info("Delete invoice for id {}", id);
    invoiceRepository.deleteInvoice(id);
  }
}
