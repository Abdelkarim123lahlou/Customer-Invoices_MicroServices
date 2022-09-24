package org.openlab.openlabcustmerservice.services;

import org.openlab.openlabcustmerservice.dto.CustomerRequestDTO;
import org.openlab.openlabcustmerservice.dto.CustomerResponseDTO;
import org.openlab.openlabcustmerservice.entities.Customer;
import org.openlab.openlabcustmerservice.mappers.CustomerMapper;
import org.openlab.openlabcustmerservice.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustmerServiceImpl implements CustomerService {


    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    public CustmerServiceImpl(CustomerRepository customerRepository,CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }



    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        //Customer customer = new Customer();
        /**
         * mapping objet Objet Objet
        la manière ancienne pour le mapping sans frameWork de mapping
         */

        /*
        customer.setId(customerRequestDTO.getId());
        customer.setName(customerRequestDTO.getName());
        customer.setMail(customerRequestDTO.getMail());
        Donner un id randome a l'id de customer (la couche service ui le fait ici
       customer.setId(UUID.randomUUID().toString);

        Customer savedCustomer = customerRepository.save(customer);
        CustomerResponseDTO customerResponse = new CustomerResponseDTO();

        customerResponse.setId(savedCustomer.getId());

        et ainsi de suite

        return customerResponse;
        */


        /**
         * l'utilisation de mapstruct
         */

        Customer customer= customerMapper.customerRequestDTOCustomer(customerRequestDTO);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerResponseDTO customerResponseDTO = customerMapper.customerToCustomerResponseDto(savedCustomer);
        return  customerResponseDTO;
    }

    @Override
    public CustomerResponseDTO getCustomer(String id) {

        Customer customer = customerRepository.findById(id).get();

        return customerMapper.customerToCustomerResponseDto(customer);
    }

    @Override
    public CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.customerRequestDTOCustomer(customerRequestDTO);
        Customer updatedCustoer = customerRepository.save(customer);
        return customerMapper.customerToCustomerResponseDto(updatedCustoer);
    }

    @Override
    public void delete(String id) {
        Customer customer= customerRepository.findById(id).get();
        customerRepository.deleteById(customer.getId());


    }

    @Override
    public List<CustomerResponseDTO> listCustomers() {
       List<Customer> customers = customerRepository.findAll();
       List<CustomerResponseDTO> customerResponseDTOS =
               customers.stream()
               .map(cust -> customerMapper.customerToCustomerResponseDto(cust)).collect(Collectors.toList());
        return customerResponseDTOS;
    }
}
