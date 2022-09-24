package com.invoiceservice.training.dto;

import com.invoiceservice.training.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceResponseDTO {
    private String id;
    private Date date;
    private BigDecimal montant;
    private Customer customer;
}
