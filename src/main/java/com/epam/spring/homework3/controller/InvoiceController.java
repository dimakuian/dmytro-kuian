package com.epam.spring.homework3.controller;

import com.epam.spring.homework3.controller.dto.InvoiceDTO;
import com.epam.spring.homework3.controller.dto.group.OnCreate;
import com.epam.spring.homework3.controller.dto.group.OnUpdate;
import com.epam.spring.homework3.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<InvoiceDTO> getAllInvoice() {
        return invoiceService.getInvoices();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public InvoiceDTO getInvoice(@PathVariable long id) {
        return invoiceService.getInvoice(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public InvoiceDTO createInvoice(@RequestBody @Validated(OnCreate.class) InvoiceDTO invoiceDTO) {
        return invoiceService.createInvoice(invoiceDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}")
    public InvoiceDTO updateInvoice(@PathVariable long id,
                                    @RequestBody @Validated(OnUpdate.class) InvoiceDTO invoiceDTO) {
        log.info("Update invoice by id {}", id);
        log.trace("Request body invoiceDTO {}", invoiceDTO);
        return invoiceService.updateInvoice(id, invoiceDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable long id) {
        log.info("Delete invoice by id {}", id);
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }

}
