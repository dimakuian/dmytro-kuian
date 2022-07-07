package com.epam.spring.cargo_delivery.service.mapper;

import com.epam.spring.cargo_delivery.controller.dto.InvoiceDTO;
import com.epam.spring.cargo_delivery.service.model.Invoice;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InvoiceMapper {

  InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

  List<InvoiceDTO> mapInvoiceDtos(List<Invoice> invoices);

  InvoiceDTO mapInvoiceDto(Invoice invoice);

  Invoice mapInvoice(InvoiceDTO invoiceDTO);
}
