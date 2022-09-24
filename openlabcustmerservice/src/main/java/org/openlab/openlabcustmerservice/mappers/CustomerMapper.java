package org.openlab.openlabcustmerservice.mappers;

import org.mapstruct.Mapper;
import org.openlab.openlabcustmerservice.dto.CustomerRequestDTO;
import org.openlab.openlabcustmerservice.dto.CustomerResponseDTO;
import org.openlab.openlabcustmerservice.entities.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    /***
     * si nous n'avons pas les memes attributs sur l'entité et le dto
     * nous utilisons l'annotation(name dans l'entité et nom dans le dto)
     * @Mapping(source ="name", target="nom") voir la documentaion
     *
     */
    CustomerResponseDTO customerToCustomerResponseDto(Customer customer);
    Customer customerRequestDTOCustomer(CustomerRequestDTO customerRequestDTO);
}
