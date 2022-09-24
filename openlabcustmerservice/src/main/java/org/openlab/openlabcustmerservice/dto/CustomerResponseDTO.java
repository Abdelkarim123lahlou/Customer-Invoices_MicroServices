package org.openlab.openlabcustmerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {

    private String id;
    private String name;
    private String mail;
}
