package com.epam.spring.cargo_delivery.controller;

import com.epam.spring.cargo_delivery.controller.assembler.InvoiceAssembler;
import com.epam.spring.cargo_delivery.controller.dto.InvoiceDTO;
import com.epam.spring.cargo_delivery.controller.model.InvoiceModel;
import com.epam.spring.cargo_delivery.service.InvoiceService;
import com.epam.spring.cargo_delivery.service.api.InvoiceApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class InvoiceController implements InvoiceApi {

  private final InvoiceService invoiceService;
  private final InvoiceAssembler invoiceAssembler;

  @Override
  public Page<InvoiceDTO> getAllInvoice(Pageable pageable) {
    log.info("Get all invoices with pageable {}", pageable);
    return invoiceService.getInvoices(pageable);
  }

  @Override
  public InvoiceModel getInvoice(long id) {
    log.info("Get invoice with id {}", id);
    InvoiceDTO outInvoiceDto = invoiceService.getInvoice(id);
    return invoiceAssembler.toModel(outInvoiceDto);
  }

  @Override
  public InvoiceModel createInvoice(InvoiceDTO invoiceDTO) {
    log.info("Create invoice {}", invoiceDTO);
    InvoiceDTO outInvoiceDto = invoiceService.createInvoice(invoiceDTO);
    return invoiceAssembler.toModel(outInvoiceDto);
  }

  @Override
  public InvoiceModel updateInvoice(long id, InvoiceDTO invoiceDTO) {
    log.info("Update invoice by id {}", id);
    log.trace("Request body invoiceDTO {}", invoiceDTO);
    InvoiceDTO outInvoiceDto = invoiceService.updateInvoice(id, invoiceDTO);
    return invoiceAssembler.toModel(outInvoiceDto);
  }

  @Override
  public ResponseEntity<Void> deleteInvoice(long id) {
    log.info("Delete invoice by id {}", id);
    invoiceService.deleteInvoice(id);
    return ResponseEntity.noContent().build();
  }

  @Override
  public InvoiceModel payInvoice(long id, InvoiceDTO invoiceDTO) {
    log.info("Pay invoice by id {}", id);
    log.trace("Request body invoiceDTO {}", invoiceDTO);
    InvoiceDTO outInvoiceDto = invoiceService.payInvoice(id, invoiceDTO);
    return invoiceAssembler.toModel(outInvoiceDto);
  }
}
