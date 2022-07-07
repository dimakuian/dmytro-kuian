package com.epam.spring.cargo_delivery.controller.model;

import com.epam.spring.cargo_delivery.controller.dto.InvoiceDTO;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class InvoiceModel extends RepresentationModel<InvoiceModel> {

  @JsonUnwrapped
  private InvoiceDTO invoiceDTO;
}
