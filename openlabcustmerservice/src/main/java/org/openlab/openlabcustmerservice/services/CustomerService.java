package org.openlab.openlabcustmerservice.services;

import org.openlab.openlabcustmerservice.dto.CustomerRequestDTO;
import org.openlab.openlabcustmerservice.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {
  CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);
  CustomerResponseDTO getCustomer(String id);
  CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO);
  void delete(String id);
  List<CustomerResponseDTO> listCustomers();

}
