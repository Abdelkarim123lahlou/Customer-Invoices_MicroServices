package com.invoiceservice.training.web;

import com.invoiceservice.training.dto.InvoiceRequestDTO;
import com.invoiceservice.training.dto.InvoiceResponseDTO;
import com.invoiceservice.training.services.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class InvoiceRestController {
    private InvoiceService invoiceService;

    public InvoiceRestController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
@GetMapping(path = "/invoices/{id}")
    public InvoiceResponseDTO getInvoice(@PathVariable(name="id") String InvoiceId){
    return invoiceService.getInvoice(InvoiceId);
    }

    @GetMapping(path = "/invoicesByCustomer/{customerId}")
    public List<InvoiceResponseDTO>getInvoicesByCustomer(@PathVariable String customerId){
        return invoiceService.invoicesByCustomer(customerId);
    }
    @PostMapping(path = "/invoices")
    public InvoiceResponseDTO save(@RequestBody InvoiceRequestDTO invoiceRequestDTO){
        return invoiceService.save(invoiceRequestDTO);
    }

    @GetMapping(path = "/invoices")
    public List<InvoiceResponseDTO> allInvoices (){
        return invoiceService.allInvoices();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
