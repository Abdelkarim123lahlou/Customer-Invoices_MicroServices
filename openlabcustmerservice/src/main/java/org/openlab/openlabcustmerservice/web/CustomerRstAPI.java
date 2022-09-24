package org.openlab.openlabcustmerservice.web;

import org.openlab.openlabcustmerservice.dto.CustomerRequestDTO;
import org.openlab.openlabcustmerservice.dto.CustomerResponseDTO;
import org.openlab.openlabcustmerservice.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/api")
public class CustomerRstAPI {
    private CustomerService customerService;

    public CustomerRstAPI(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/customres")
    public List<CustomerResponseDTO> allcustomers(){
        return customerService.listCustomers();
    }

    @PostMapping(path = "/saveCustomers")
    public CustomerResponseDTO save(@RequestBody CustomerRequestDTO customerRequestDTO){
        customerRequestDTO.setId(UUID.randomUUID().toString());
        return customerService.save(customerRequestDTO);
    }

    @GetMapping(path = "/customer/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable String id){
        return customerService.getCustomer(id);
    }
    @DeleteMapping(path = "/deleteCustomer/{id}")
    public void DeleteCustomer(@PathVariable String id) {
        customerService.delete(id) ;
    }

}
