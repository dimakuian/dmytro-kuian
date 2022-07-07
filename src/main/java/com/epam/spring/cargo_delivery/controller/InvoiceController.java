package com.epam.spring.cargo_delivery.controller;

import com.epam.spring.cargo_delivery.controller.assembler.InvoiceAssembler;
import com.epam.spring.cargo_delivery.controller.dto.InvoiceDTO;
import com.epam.spring.cargo_delivery.controller.model.InvoiceModel;
import com.epam.spring.cargo_delivery.service.InvoiceService;
import com.epam.spring.cargo_delivery.service.api.InvoiceApi;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class InvoiceController implements InvoiceApi {

  private final InvoiceService invoiceService;
  private final InvoiceAssembler invoiceAssembler;

  @Override
  public List<InvoiceDTO> getAllInvoice() {
    return invoiceService.getInvoices();
  }

  @Override
  public InvoiceModel getInvoice(long id) {
    InvoiceDTO outInvoiceDto = invoiceService.getInvoice(id);
    return invoiceAssembler.toModel(outInvoiceDto);
  }

  @Override
  public InvoiceModel createInvoice(InvoiceDTO invoiceDTO) {
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
}
