package com.invoiceservice.training.openfeign;

import com.invoiceservice.training.entities.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="CUSTOMER-SERVICE"/* nom comme spécifie dans application.properties du service customer*/)
public interface CustomerRestClient {
    @GetMapping(path = "/api/customer/{id}")
    Customer getCustomer(@PathVariable(name="id") String id);

    @GetMapping(path = "/api/customers")
    List<Customer>allcustomers();
}
