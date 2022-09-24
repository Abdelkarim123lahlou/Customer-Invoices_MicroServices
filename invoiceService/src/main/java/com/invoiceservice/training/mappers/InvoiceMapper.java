package com.invoiceservice.training.mappers;

import com.invoiceservice.training.dto.InvoiceRequestDTO;
import com.invoiceservice.training.dto.InvoiceResponseDTO;
import com.invoiceservice.training.entities.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice fromInvoiceRequestDTO(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO fromInvoice(Invoice invoice);
}
