package com.invoiceservice.training.repositories;

import com.invoiceservice.training.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,String> {
    List<Invoice> findByCustomerId(String customerId);
}
