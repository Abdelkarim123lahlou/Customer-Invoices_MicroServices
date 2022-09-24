package com.invoiceservice.training.services;

import com.invoiceservice.training.dto.InvoiceRequestDTO;
import com.invoiceservice.training.dto.InvoiceResponseDTO;
import com.invoiceservice.training.entities.Customer;
import com.invoiceservice.training.entities.Invoice;
import com.invoiceservice.training.exceptions.CustomerNotFoundExceptions;
import com.invoiceservice.training.mappers.InvoiceMapper;
import com.invoiceservice.training.openfeign.CustomerRestClient;
import com.invoiceservice.training.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;
    private CustomerRestClient customerRestClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, CustomerRestClient customerRestClient) {
        // a la création de cet constructeur on va avoir une erreur dans CustomerRestClient parceque on n'a pas activer OpenFeign
        // pour le fair dans notre applicaption spring on ajout l 'annotation @EnableFeingClient
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) {
        Customer customer = null;

        /*
         * Vérification de l'integrité référencielle Invoice / customer c a d verifier si le customer existe ou pas
         * vu que nous ne devons pas ajouter une facture d'un cutomer qui n'existe pas(c'est le try catch)
         * */
        try {
              customer = customerRestClient.getCustomer(invoiceRequestDTO.getCustomerId());
        }
        catch (Exception e) {
            throw new CustomerNotFoundExceptions("Customer Not Found");
        }// le try catche c'est pour ne pas enregistrer une facture pour un client qui n'existe pas
        Invoice invoice = invoiceMapper.fromInvoiceRequestDTO(invoiceRequestDTO);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());

        Invoice savedInvoice = invoiceRepository.save(invoice);
        savedInvoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(savedInvoice);
    }

    @Override
    public InvoiceResponseDTO getInvoice(String invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).get();// dans cette ligne si il ne trouve pas de résultat il génére une exception
        Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
        invoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> invoicesByCustomer(String customerId) {
        List<Invoice> invoices = invoiceRepository.findByCustomerId(customerId);
        return invoices.
                stream().
                map(invoice -> invoiceMapper.fromInvoice(invoice))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceResponseDTO> allInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        for (Invoice invoice : invoices){
            Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
        }
        return invoices.stream().map(invo -> invoiceMapper.fromInvoice(invo)).collect(Collectors.toList());
    }
}
