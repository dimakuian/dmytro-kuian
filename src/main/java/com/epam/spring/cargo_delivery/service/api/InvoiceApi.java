package com.epam.spring.cargo_delivery.service.api;

import com.epam.spring.cargo_delivery.controller.dto.InvoiceDTO;
import com.epam.spring.cargo_delivery.controller.dto.group.OnCreate;
import com.epam.spring.cargo_delivery.controller.dto.group.OnUpdate;
import com.epam.spring.cargo_delivery.controller.model.InvoiceModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

@Api(tags = "Invoice management API")
@RequestMapping("api/v1/invoice")
public interface InvoiceApi {

  @ApiOperation("Get all invoice")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  Page<InvoiceDTO> getAllInvoice(Pageable pageable);

  @ApiImplicitParams({
      @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Invoice id")
  })
  @ApiOperation("Get invoice")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/{id}")
  InvoiceModel getInvoice(@PathVariable long id);

  @ApiOperation("Create invoice")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  InvoiceModel createInvoice(@RequestBody @Validated(OnCreate.class) InvoiceDTO invoiceDTO);

  @ApiImplicitParams({
      @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Invoice id")
  })
  @ApiOperation("Update invoice")
  @ResponseStatus(HttpStatus.OK)
  @PatchMapping(value = "/{id}")
  InvoiceModel updateInvoice(@PathVariable long id,
      @RequestBody @Validated(OnUpdate.class) InvoiceDTO invoiceDTO);

  @ApiImplicitParams({
      @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Invoice id")
  })
  @ApiOperation("Delete invoice")
  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping(value = "/{id}")
  ResponseEntity<Void> deleteInvoice(@PathVariable long id);

  @ApiImplicitParams({
      @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Invoice id")
  })
  @ApiOperation("Pay invoice")
  @ResponseStatus(HttpStatus.OK)
  @PatchMapping(value = "/{id}/pay")
  InvoiceModel payInvoice(@PathVariable long id,
      @RequestBody @Validated(OnUpdate.class) InvoiceDTO invoiceDTO);
}
