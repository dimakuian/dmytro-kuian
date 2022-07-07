package com.epam.spring.cargo_delivery.controller;

import com.epam.spring.cargo_delivery.controller.assembler.InvoiceAssembler;
import com.epam.spring.cargo_delivery.controller.dto.InvoiceDTO;
import com.epam.spring.cargo_delivery.controller.dto.group.OnCreate;
import com.epam.spring.cargo_delivery.controller.dto.group.OnUpdate;
import com.epam.spring.cargo_delivery.controller.model.InvoiceModel;
import com.epam.spring.cargo_delivery.service.InvoiceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/invoice")
@RequiredArgsConstructor
public class InvoiceController {

  private final InvoiceService invoiceService;
  private final InvoiceAssembler invoiceAssembler;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<InvoiceDTO> getAllInvoice() {
    return invoiceService.getInvoices();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/{id}")
  public InvoiceModel getInvoice(@PathVariable long id) {
    InvoiceDTO outInvoiceDto = invoiceService.getInvoice(id);
    return invoiceAssembler.toModel(outInvoiceDto);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public InvoiceModel createInvoice(@RequestBody @Validated(OnCreate.class) InvoiceDTO invoiceDTO) {
    InvoiceDTO outInvoiceDto = invoiceService.createInvoice(invoiceDTO);
    return invoiceAssembler.toModel(outInvoiceDto);
  }

  @ResponseStatus(HttpStatus.OK)
  @PatchMapping(value = "/{id}")
  public InvoiceModel updateInvoice(@PathVariable long id,
      @RequestBody @Validated(OnUpdate.class) InvoiceDTO invoiceDTO) {
    log.info("Update invoice by id {}", id);
    log.trace("Request body invoiceDTO {}", invoiceDTO);
    InvoiceDTO outInvoiceDto = invoiceService.updateInvoice(id, invoiceDTO);
    return invoiceAssembler.toModel(outInvoiceDto);
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteInvoice(@PathVariable long id) {
    log.info("Delete invoice by id {}", id);
    invoiceService.deleteInvoice(id);
    return ResponseEntity.noContent().build();
  }
}
