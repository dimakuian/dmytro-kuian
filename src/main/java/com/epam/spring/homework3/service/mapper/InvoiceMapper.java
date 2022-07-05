package com.epam.spring.homework3.service.mapper;

import com.epam.spring.homework3.controller.dto.InvoiceDTO;
import com.epam.spring.homework3.service.model.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface InvoiceMapper {

    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    List<InvoiceDTO> mapInvoiceDtos(List<Invoice> invoices);

    InvoiceDTO mapInvoiceDto(Invoice invoice);

    Invoice mapInvoice(InvoiceDTO invoiceDTO);
}
