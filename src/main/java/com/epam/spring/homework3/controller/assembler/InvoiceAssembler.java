package com.epam.spring.homework3.controller.assembler;

import com.epam.spring.homework3.controller.InvoiceController;
import com.epam.spring.homework3.controller.dto.InvoiceDTO;
import com.epam.spring.homework3.controller.model.InvoiceModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class InvoiceAssembler extends RepresentationModelAssemblerSupport<InvoiceDTO, InvoiceModel> {

    public static final String GET_REL = "get_invoice";
    public static final String CREATE_REL = "create_invoice";
    public static final String UPDATE_REL = "update_invoice";
    public static final String DELETE_REL = "delete_invoice";

    public InvoiceAssembler(Class<?> controllerClass, Class<InvoiceModel> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    public InvoiceModel toModel(InvoiceDTO entity) {
        InvoiceModel invoiceModel = new InvoiceModel(entity);
        Link get = linkTo(methodOn(InvoiceController.class).getInvoice(entity.getId())).withRel(GET_REL);
        Link create = linkTo(methodOn(InvoiceController.class).createInvoice(entity)).withRel(CREATE_REL);
        Link update = linkTo(methodOn(InvoiceController.class).updateInvoice(entity.getId(), entity)).withRel(UPDATE_REL);
        Link delete = linkTo(methodOn(InvoiceController.class).deleteInvoice(entity.getId())).withRel(DELETE_REL);

        invoiceModel.add(get, create, update, delete);
        return invoiceModel;
    }
}
