package com.invoiceservice.training.services;

import com.invoiceservice.training.dto.InvoiceRequestDTO;
import com.invoiceservice.training.dto.InvoiceResponseDTO;

import java.util.List;

public interface InvoiceService {
    InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO getInvoice(String invoiceId);
    List<InvoiceResponseDTO> invoicesByCustomer(String customerId);

    List<InvoiceResponseDTO> allInvoices();
}
